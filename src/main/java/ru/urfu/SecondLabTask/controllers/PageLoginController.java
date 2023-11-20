package ru.urfu.SecondLabTask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.services.UserService;

@Controller
public class PageLoginController {
    private final UserService userService;

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @PostMapping({"/login"})
    public String adduser(@ModelAttribute final UserDTO userDTO, final Model model) {
        try {
            this.userService.tryLogin(userDTO);
            return "loginSuccess";
        } catch (Exception ex) {
            model.addAttribute("message", (Object) "Неправильный пароль");
            return "login";
        }
    }
    public PageLoginController(final UserService userService) {
        this.userService = userService;
    }
}

