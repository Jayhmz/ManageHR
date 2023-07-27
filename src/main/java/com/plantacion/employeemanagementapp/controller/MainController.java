package com.plantacion.employeemanagementapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/app")
public class MainController {
    @GetMapping("/home")
    public String showHomePage(Principal principal, Model model){
        model.addAttribute("authUser", principal.getName());
        return "homepage";
    }
    @GetMapping("/staff/create")
    public String showCreateUserPage(){
        return "createuser";
    }
}
