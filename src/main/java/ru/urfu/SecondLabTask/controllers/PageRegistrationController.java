package ru.urfu.SecondLabTask.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageRegistrationController {

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
}
