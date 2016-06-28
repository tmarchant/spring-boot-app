package com.johnlewis.web.pages.home;

import com.johnlewis.web.pages.ApplicationUrn;
import com.johnlewis.web.pages.Page;
import com.johnlewis.web.webdriver.FriendlyWebDriver

import static com.johnlewis.web.pages.ApplicationUrn.home;

class HomePage extends Page {

    HomePage(FriendlyWebDriver driver, URL baseUrl) {
        super(driver, baseUrl, home)
    }
}
