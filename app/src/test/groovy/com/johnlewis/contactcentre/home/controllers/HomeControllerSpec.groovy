package com.johnlewis.contactcentre.home.controllers

import org.springframework.ui.ExtendedModelMap
import spock.lang.Specification

class HomeControllerSpec extends Specification {

    def "example controller test" () {
        given:
        def controller = new HomeController()

        expect:
        controller.index(new ExtendedModelMap()) == 'home'
    }

}
