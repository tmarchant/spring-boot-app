package com.johnlewis.htmlunit

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.WebRequest
import com.gargoylesoftware.htmlunit.WebResponse
import com.gargoylesoftware.htmlunit.util.Cookie
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection

class CookieSupportWebConnection extends WebConnectionWrapper {

    private WebClient webClient

    CookieSupportWebConnection(WebClient webClient, MockMvcWebConnection webConnection) throws IllegalArgumentException {
        super(webConnection)
        this.webClient = webClient
        webConnection.webClient = webClient
    }

    @Override
    WebResponse getResponse(WebRequest request) throws IOException {
        if (webClient.cookieManager.cookies) {
            request.setAdditionalHeader('Cookie', buildCookieHeaderValue(webClient.cookieManager.cookies))
        }

        def response = super.getResponse(request)
        response.responseHeaders
                .findAll { it.name == 'Set-Cookie' }
                .collect { it.value }
                .each { webClient.cookieManager.addCookie(parseSetCookieHeaderValue(it, request.url.host)) }

        return response
    }

    private Cookie parseSetCookieHeaderValue(String headerValue, currentDomain) {
        def (String name, String rest) = headerValue.split('=', 2)
        def parts = rest.split('; *')
        def value = parts.first()
        def attributes = parts.drop(1)
        def attributesMap = attributes.collectEntries {
            if (it.contains('=')) {
                return it.split('=', 2) as List
            } else {
                return [it, true]
            }
        }.collectEntries { k,v -> [k.toLowerCase(), v] }

        def domain = attributesMap['domain'] ?: currentDomain
        def path = attributesMap['path']
        def maxAge = (attributesMap['max-age'] ?: -1) as int
        def secure = attributesMap['secure'] ?: false
        def httpOnly = attributesMap['httponly'] ?: false

        def expires = null
        if (maxAge >= 0) {
            expires = new Date(System.currentTimeMillis() + (maxAge * 1000L));
        }

        new Cookie(domain, name, value, path, expires, secure, httpOnly)
    }

    private String buildCookieHeaderValue(Set<Cookie> cookies) {
        return cookies.collect { it.name + '=' + it.value }.join('; ')
    }

}
