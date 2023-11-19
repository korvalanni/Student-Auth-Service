package ru.urfu.SecondLabTask.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.urfu.SecondLabTask.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import ru.urfu.SecondLabTask.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class PageRegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.addUser(userDTO);
            return "registration";
        } catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "success";
    }

    public PageRegistrationController(UserService userService) {
        this.userService = userService;
    }
}
