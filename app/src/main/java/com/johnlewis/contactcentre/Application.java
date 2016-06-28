package com.johnlewis.contactcentre;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = Application.class)
@PropertySource(value = "classpath:/default.properties") // will have lower priority than application.properties files
public class Application {
    public static void main(String[] args) {
        specifyApplicationPropertiesFileNames();
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    private static void specifyApplicationPropertiesFileNames() {
        System.setProperty("spring.config.name", "application,features");
    }


}

