package com.johnlewis.web.webdriver

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.htmlunit.HtmlUnitDriver

class FriendlyWebDriver implements WebDriver {

    @Delegate
    private final WebDriver driver

    FriendlyWebDriver(WebDriver driver) {
        this.driver = driver
    }

    URL getCurrentBaseUrl() {
        def fullUri = driver.currentUrl.toURI()
        "${fullUri.scheme}://${fullUri.authority}".toURL()
    }

    def setJavascriptEnabled(boolean enabled) {
        if(driver instanceof HtmlUnitDriver) {
            ((HtmlUnitDriver) driver).setJavascriptEnabled(enabled);
        }
    }

    Object executeScript(String script, final Object... args) {
        return ((JavascriptExecutor)driver).executeScript(script, args);
    }

    def <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        if (driver instanceof TakesScreenshot) {
            return (driver as TakesScreenshot).getScreenshotAs(target)
        } else {
            return null
        }
    }

    def isHeadless() {
        return this.driver instanceof HtmlUnitDriver;
    }
}
