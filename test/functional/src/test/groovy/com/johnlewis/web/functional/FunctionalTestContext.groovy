package com.johnlewis.web.functional

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler
import com.gargoylesoftware.htmlunit.WebClient
import com.johnlewis.htmlunit.CookieSupportWebConnection
import com.johnlewis.web.pages.Browser
import com.johnlewis.web.webclient.Client
import com.johnlewis.web.webdriver.FriendlyWebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Configuration
@Profile('functional-test')
class FunctionalTestContext {

    @Autowired
    WebApplicationContext webApplicationContext

    @Bean
    @Scope('prototype')
    Browser browser() {
        return new Browser(webDriver(), new URL("http://localhost:3030"))
    }

    @Bean
    @Scope('prototype')
    FriendlyWebDriver webDriver() {
        def htmlUnitDriver = new HtmlUnitDriver() {
            @Override
            WebClient modifyWebClient(WebClient client) {
                def mockMvc = MockMvcBuilders.webAppContextSetup(FunctionalTestContext.this.webApplicationContext).build()
                def webClient = new WebClient()
                webClient.cssErrorHandler = new SilentCssErrorHandler()

                def cookieSupportWebConnection = new CookieSupportWebConnection(webClient, new MockMvcWebConnection(mockMvc))
                webClient.setWebConnection(cookieSupportWebConnection)
                webClient.options.with {
                    setThrowExceptionOnFailingStatusCode(false)
                    setCssEnabled(false)
                    setHomePage('about:blank')
                }
                return webClient
            }
        }
        htmlUnitDriver.setJavascriptEnabled(false)
        new FriendlyWebDriver(htmlUnitDriver)
    }


    @Bean
    @Scope('prototype')
    Client client() {
        def mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        def factory = new MockMvcClientHttpRequestFactory(mockMvc)
        return new Client(factory, new URL("http://localhost:3030"))
    }
}