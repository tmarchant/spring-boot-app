package com.johnlewis.web.pages

import com.johnlewis.web.pages.components.PageComponent
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class Head extends PageComponent {

    Head(WebElement header) {
        super(header)
    }

    String getTitle() {
        me.findElement(By.tagName('title')).text
    }
}
