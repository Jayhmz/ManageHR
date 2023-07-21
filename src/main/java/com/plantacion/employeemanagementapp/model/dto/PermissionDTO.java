package com.plantacion.employeemanagementapp.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermissionDTO {
    @NotNull(message = "field cannot be empty")
    private String title;
}
