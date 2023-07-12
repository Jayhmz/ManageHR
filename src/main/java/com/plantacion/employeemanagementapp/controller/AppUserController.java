package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService service;

    @GetMapping("/test")
    public String test(@ModelAttribute AppUserDTO userDTO, Model model){

        model.addAttribute("message", "this is the mesasge i wnat to tdisplay");
        return "index";
    }

    public String signUp(@Valid @ModelAttribute AppUserDTO userDTO, BindingResult result){

        return "homepage";
    }
}
