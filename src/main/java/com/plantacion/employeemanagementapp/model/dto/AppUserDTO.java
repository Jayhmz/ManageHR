package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AppUserDTO {

    @NotNull(message = "Firstname field cannot be null")
    private String firstname;
    @NotNull(message = "Lastname field cannot be null")
    private String lastname;
    @Email(message = "Enter valid email")
    @NotNull(message = "Email field cannot be null")
    private String email;
    @NotNull(message = "Password field cannot be null")
    @Size(min = 8, message = "Enter at least 8 characters")
    private String password;
    @NotNull(message = "Confirm password")
    @Size(min = 8, message = "Enter at least 8 characters")
    private String confirmPassword;
    @NotNull(message = "Select your role")
    private List<RoleDTO> roleDTO;
}
