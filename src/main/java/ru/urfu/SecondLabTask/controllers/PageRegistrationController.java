package ru.urfu.SecondLabTask.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.urfu.SecondLabTask.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import ru.urfu.SecondLabTask.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class PageRegistrationController
{
    private final UserService userService;

    @GetMapping({ "/registration" })
    public String registration() {
        return "registration";
    }

    @PostMapping({ "/registration" })
    public String adduser(@ModelAttribute final UserDTO userDTO, final Model model) {
        try {
            this.userService.addUser(userDTO);
            return "registrationSuccess";
        }
        catch (Exception ex) {
            model.addAttribute("message", (Object)"User exists");
            return "registration";
        }
    }

    public PageRegistrationController(final UserService userService) {
        this.userService = userService;
    }
}