package com.johnlewis.web.pages

import com.google.common.io.Files
import com.johnlewis.web.webdriver.FriendlyWebDriver
import lombok.extern.slf4j.Slf4j
import org.openqa.selenium.OutputType
import org.openqa.selenium.WebDriverException

@Slf4j
class Browser {

    private final FriendlyWebDriver webDriver
    private final URL baseUrl

    Browser(FriendlyWebDriver webDriver, URL baseUrl){
        this.webDriver = webDriver
        this.baseUrl = baseUrl
    }

    public <P extends Page> P visit(Class<P> clazz, Map pathParams = [:], Map queryParams = [:]){
        return Page.open(webDriver, clazz, baseUrl, pathParams, queryParams)
    }

    String getCurrentUrl() {
        return webDriver.currentUrl
    }

    def setJavascriptEnabled(enabled) {
        webDriver.setJavascriptEnabled(enabled);
    }

    URL getCurrentBaseUrl() {
        webDriver.getCurrentBaseUrl()
    }

    void capturePageInfo() {
        log.info(webDriver.currentUrl)
        log.info(webDriver.pageSource)
    }

    void captureScreenshot() {
        try {
            def screenshot = webDriver.getScreenshotAs(OutputType.FILE);
            def directoryPath = System.getProperty("screenshotDir", "build/screenshots")
            def directory = new File(directoryPath)
            directory.mkdirs()
            def targetFile = new File("build/screenshots", screenshot.name)
            log.info("saving screenshot to {}", targetFile.absolutePath)
            Files.copy(screenshot, targetFile)
        } catch (WebDriverException e) {
            log.error(e)
        } catch (IOException e) {
            log.error(e)
        }
    }

    def unconditionalWait() {
        webDriver.unconditionalWait()
    }
}
