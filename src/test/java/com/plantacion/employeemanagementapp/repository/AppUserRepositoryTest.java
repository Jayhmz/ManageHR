package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AppUserRepository test for")
class AppUserRepositoryTest {

    @Mock
    private AppUserRepository repository;
    @Mock
    private AppUser user;

    @Test
    @DisplayName("finding user with valid email")
    void findByEmail() {
        when(repository.findByEmail("a@a.com")).thenReturn(user);
        assertEquals(user, repository.findByEmail("a@a.com"), "when the user email exists");
    }

    @Test
    @DisplayName("finding user with invalid email")
    void findByEmailNotExisting() {
        when(repository.findByEmail("a@a.com")).thenReturn(null);
        assertEquals(null, repository.findByEmail("a@a.com"), "when the user email does not exists");
    }
}