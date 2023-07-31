package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import com.plantacion.employeemanagementapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/app")
public class MainController {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/home")
    public String showHomePage(Principal principal, Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size){
        AppUser user = appUserRepository.findByEmail(principal.getName());
        model.addAttribute("authUser", user.getFirstname());
        Page<AppUser> pagenatedUsers = appUserService.getPagenatedUsers(page, size);
        model.addAttribute("paginatedUsers", pagenatedUsers);
        return "homepage";
    }
    @GetMapping("/staff/create")
    public String showCreateUserPage(){
        return "createuser";
    }
}
