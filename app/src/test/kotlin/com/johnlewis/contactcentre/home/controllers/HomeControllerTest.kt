package com.johnlewis.contactcentre.home.controllers

import org.junit.Test
import org.springframework.ui.ExtendedModelMap
import kotlin.test.assertEquals

class HomeControllerTest {

    @Test
    fun exampleUnitTestInKotlin() {
        val homeController = HomeController()
        val extendedModelMap = ExtendedModelMap()

        assertEquals("home", homeController.index(extendedModelMap))
    }
}