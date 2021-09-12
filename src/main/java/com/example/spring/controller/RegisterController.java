package com.example.spring.controller;

import com.example.hibernate.entity.User;
import com.example.spring.service.RoleService;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserRegistration newUser = new UserRegistration();
        LOGGER.log(Level.INFO, "Passed to model: {0}", newUser);
        model.addAttribute("newUser", newUser);
        return "registration-form";
    }

    @PostMapping("/process-registration")
    public String processRegistration(@Valid @ModelAttribute("newUser") UserRegistration newUser,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationError",
                    result.getFieldError().getDefaultMessage());
            return "registration-form";
        }

        if (userService.getUserByUsername(newUser.getUsername()) != null) {
            model.addAttribute("registrationError", "User name already exists.");
            return "registration-form";
        }

        User user = userService.userOf(newUser);
        user.addRoles(roleService.getRoleByName("ROLE_USER"));

        LOGGER.log(Level.INFO,"Saving user: {0}", user);
        userService.saveUser(user);
        return "login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
