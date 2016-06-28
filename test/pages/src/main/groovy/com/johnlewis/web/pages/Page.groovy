package com.johnlewis.web.pages

import com.johnlewis.web.pages.components.Header
import com.johnlewis.web.webdriver.FriendlyWebDriver
import lombok.extern.slf4j.Slf4j
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory

@Slf4j
abstract class Page {

    protected FriendlyWebDriver driver
    protected final URL baseUrl
    protected final ApplicationUrn applicationUrn
    protected final Head head

    Page(FriendlyWebDriver driver, URL baseUrl, ApplicationUrn applicationUrn) {
        this.applicationUrn = applicationUrn
        this.driver = driver
        this.baseUrl = baseUrl

        PageFactory.initElements(driver, this)
        this.head = new Head(driver.findElement(By.tagName('head')));

    }

    protected Page visit(Map pathParams = [:], Map queryParams = [:]){
        driver.navigate().to(applicationUrn.toUrl(baseUrl, pathParams, queryParams))
        this
    }

    protected Page visit(String urn){
        driver.navigate().to(new URL(baseUrl, urn))
        this
    }

    static <P extends Page> P open(FriendlyWebDriver driver, Class<P> clazz, URL url, Map pathParams = [:], Map queryParams = [:]){
        clazz.cast(clazz.newInstance(driver, url).visit(pathParams, queryParams))
    }

    static <P extends Page> P open(FriendlyWebDriver driver, Class<P> clazz, URL url, String urn){
        clazz.cast(clazz.newInstance(driver, url).visit(urn))
    }

    Header getHeader() {
        new Header(driver.findElement(By.tagName('header')), driver)
    }
}


