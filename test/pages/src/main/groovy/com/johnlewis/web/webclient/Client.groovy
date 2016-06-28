package com.johnlewis.web.webclient

import com.johnlewis.web.pages.ApplicationUrn
import com.johnlewis.web.pages.Page
import org.springframework.http.HttpMethod
import org.springframework.http.client.ClientHttpRequestFactory

public class Client {

    private final ClientHttpRequestFactory requestFactory

    final URL baseUrl

    public Client(ClientHttpRequestFactory requestFactory, URL baseUrl){
        this.requestFactory = requestFactory
        this.baseUrl = baseUrl
    }

    public <P extends Page> Response visit(Class<P> clazz, Map pathParams = [:], Map queryParams = [:]){
        def page = ApplicationUrn.forPage(clazz);
        def url = page.toUrl(baseUrl, pathParams, queryParams)
        return visit(url.toExternalForm())
    }

    public <P extends Page> Response visit(String uri){
        def fullUri = baseUrl.toURI().resolve(uri)

        def request = requestFactory.createRequest(fullUri, HttpMethod.GET)
        def response = request.execute();
        return new Response(response);
    }
}
