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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService service;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String index(@ModelAttribute("userDTO") AppUserDTO userDTO, Model model){
        List<Role> roles = roleService.fetchAllRoles();
        model.addAttribute("roles", roles);
        for(Role role: roles){
            System.out.println(role.getTitle());
        }
        model.addAttribute("message", "this is the mesasge i wnat to tdisplay");
        return "index";
    }

    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute AppUserDTO userDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            model.addAttribute("errors", allErrors);
            //this foreach should be removed before production
            for (ObjectError error: allErrors) {
                System.out.println(">>>> "+error +" \n");
            }
            return "redirect:/";
        }
        service.createUser(userDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    @ResponseBody
    public String loginPage(){
        return "this is cool now...";
    }
}
