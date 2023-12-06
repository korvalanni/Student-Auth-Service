package ru.urfu.SecondLabTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProjectRegistrationController {
    @GetMapping("/project")
    public String projectRegistration() {
        return "projectRegistration";
    }

}
