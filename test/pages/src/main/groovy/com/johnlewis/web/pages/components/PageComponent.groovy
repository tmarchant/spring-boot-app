package com.johnlewis.web.pages.components

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class PageComponent {

    protected final WebElement me

    PageComponent(WebElement scope) {
        this.me = scope
    }

    def getTextIgnoringAudibles() {
        def audibles = me.findElements(By.className('u-audible')).collect{aud -> aud.getText()}
        def linkText = getText()

        audibles.each{ linkText = linkText.replace(it, "") }

        linkText.trim()
    }

    def getTextIgnoringInnerSpans() {
        def spans = me.findElements(By.tagName('span')).collect{aud -> aud.getText()}
        def linkText = getText()

        spans.each{ linkText = linkText.replace(it, "") }

        linkText.trim()
    }

    def getText() {
        me.getText()
    }

    boolean containsElement(By selector) {
        !me.findElements(selector).empty
    }
}