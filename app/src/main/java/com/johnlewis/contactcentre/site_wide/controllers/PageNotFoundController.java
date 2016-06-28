package com.johnlewis.contactcentre.site_wide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageNotFoundController {

    @RequestMapping("/404")
    public String pageNotFound() {
        return "404";
    }
}
