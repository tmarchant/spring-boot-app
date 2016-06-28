package com.johnlewis.webdriver

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import org.openqa.selenium.WebElement

class WebElementExtensions {
    static boolean hasClass(WebElement self, String text) {
        getClasses(self).contains(text)
    }

    static boolean hasClassWhere(WebElement self,
                                 @ClosureParams(value = SimpleType, options = 'java.lang.String') Closure<Boolean> predicate) {
        getClasses(self).any(predicate)
    }

    private static String[] getClasses(WebElement element) {
        element.getAttribute('class').split()
    }

}
