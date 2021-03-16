package com.genesys.techtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/", ""})
    public String index() {
        return "index";
    }

    @PostMapping(value = {"/general_error"})
    public String triggerGeneralError() {
        return "index";
    }

}