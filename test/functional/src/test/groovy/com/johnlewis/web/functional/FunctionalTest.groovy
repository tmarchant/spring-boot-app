package com.johnlewis.web.functional

import com.johnlewis.contactcentre.Application
import groovy.transform.AnnotationCollector
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = [Application, FunctionalTestContext])
@WebAppConfiguration
@TestPropertySource('/functional-test.properties')
@ActiveProfiles(['stub-repositories', 'functional-test'])
@AnnotationCollector
@interface FunctionalTest {}