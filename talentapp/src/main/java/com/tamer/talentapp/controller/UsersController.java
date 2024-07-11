package com.tamer.talentapp.controller;

import com.tamer.talentapp.entity.Users;
import com.tamer.talentapp.entity.UsersType;
import com.tamer.talentapp.services.UsersService;
import com.tamer.talentapp.services.UsersTypeService;
import jakarta.validation.Valid;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> userTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model) {
        Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail());


        if (optionalUsers.isPresent()) {
            model.addAttribute("error", "Email already registered, try to login or register with other email.");
            List<UsersType> userTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", userTypes);
            model.addAttribute("user", new Users());
            return "register";
        }

        usersService.addNew(users);
        return "dashboard";
    }

}
