package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInDTO {
    @NotNull(message = "Username field cannot be empty")
    private String username;
    @NotNull(message = "Password field cannot be empty")
    private String password;
}
