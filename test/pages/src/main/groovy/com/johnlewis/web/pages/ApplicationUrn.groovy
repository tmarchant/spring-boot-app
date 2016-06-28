package com.johnlewis.web.pages

import com.johnlewis.web.pages.home.HomePage
import groovy.text.GStringTemplateEngine
import groovy.text.Template

enum ApplicationUrn {
    home('/', HomePage);

    private final Class<? extends Page> pageClass
    private final Template template

    ApplicationUrn(String template, Class<? extends Page> pageClass){
        this.template = new GStringTemplateEngine().createTemplate("${template}")
        this.pageClass = pageClass
    }

    def URL toUrl(URL baseUrl, Map pathParams = [:], Map queryParams = [:]) {

        def parametersWithDefault = pathParams.withDefault { key  -> "MISSING: ${key}"}

        String queryArgs = '';
        if (!queryParams.isEmpty()) {
            queryArgs = '?' +
                    queryParams.collect {k,v ->
                        "${k}=" + URLEncoder.encode(v, 'UTF-8')
                    }.join('&')
        }

        return new URL(baseUrl, template.make(parametersWithDefault).toString() + queryArgs)
    }

    static ApplicationUrn forPage(Class<? extends Page> pageClass){
        values().find{it.pageClass == pageClass}
    }
}