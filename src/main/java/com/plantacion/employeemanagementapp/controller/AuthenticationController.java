package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.service.AppUserService;
import com.plantacion.employeemanagementapp.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AppUserService service;
    @Autowired
    private RoleService roleService;

    @GetMapping("/register")
    public String index(@ModelAttribute("userDTO") AppUserDTO userDTO, Model model){
        List<Role> roles = roleService.fetchAllRoles();
        model.addAttribute("roles", roles);
        return "index";
    }
    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute AppUserDTO userDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            model.addAttribute("errors", allErrors);
            return "redirect:/";
        }
        service.createUser(userDTO);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String showLoginPage(){
        return "loginpage";
    }


}
