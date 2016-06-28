package com.johnlewis.web.functional.home

import com.johnlewis.web.functional.FunctionalTest
import com.johnlewis.web.pages.Browser
import com.johnlewis.web.pages.home.HomePage
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

@FunctionalTest
class HomePageTest {

    @Autowired
    Browser browser

    @Test
    void "should render the homepage with unicorn"() {
        def homePage = browser.visit(HomePage)
        assert true
    }
}
