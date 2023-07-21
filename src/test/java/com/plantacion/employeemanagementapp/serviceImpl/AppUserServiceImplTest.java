package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import com.plantacion.employeemanagementapp.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AppUser service test to")
class AppUserServiceImplTest {

    @InjectMocks
    private AppUserServiceImpl appUserService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder encoder;

    // Test data
    private AppUserDTO userDTO;
    private AppUser user;
    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp() {
        // Create the roles
        role1 = new Role(1, "ADMIN");
        role2 = new Role(2, "STAFF");

        // Create appUserDTO
        userDTO = new AppUserDTO();
        userDTO.setEmail("muyiwa@gmail.com");
        userDTO.setFirstname("Muyiwa");
        userDTO.setLastname("Ademola");
        userDTO.setPassword("password");
        userDTO.setUserRolesId(Arrays.asList(1, 2));
    }

    @Test
    @DisplayName("test for create user with correct details")
    void createUserWithCorrectDetails() {
        // Mock roleService.findbyId() method to return the roles
        when(roleService.findbyId(1)).thenReturn(Optional.of(role1));
        when(roleService.findbyId(2)).thenReturn(Optional.of(role2));

        user = AppUser.builder()
                .email(userDTO.getEmail())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .password(userDTO.getPassword())
                .build();
        List<Role> userRole = new ArrayList<>();
        for (int role : userDTO.getUserRolesId()) {
            Optional<Role> optionalRole = roleService.findbyId(role);
            userRole.add(optionalRole.get());
        }
        user.setRole(userRole);

        when(encoder.encode(userDTO.getPassword())).thenReturn(userDTO.getPassword());

        // Mock appUserRepository.save() method to return the user
        when(appUserRepository.save(ArgumentMatchers.any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //when(appUserRepository.save(user)).thenAnswer(invocation -> invocation.getArguments());

        // Call the method being tested
        AppUser createdUser = appUserService.createUser(userDTO);

        // Assert the result
        assertNotNull(createdUser, "Created user should not be null");
        assertEquals(userDTO.getEmail(), createdUser.getEmail(), "Email should match");
        assertEquals(userDTO.getFirstname(), createdUser.getFirstname(), "Firstname should match");
        assertEquals(userDTO.getLastname(), createdUser.getLastname(), "Lastname should match");
        // Add more assertions based on your business logic for creating a user
    }

    @Test
    @DisplayName("test for create user with incorrect details")
    void createUserWithNull() {
        // Mock appUserRepository.save() method to return the roles
        when(appUserRepository.save(ArgumentMatchers.any(AppUser.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Mock appUserService.createUser() method to return null
        when(appUserService.createUser(userDTO)).thenReturn(null);

        // Call the method being tested
        AppUser createdUser = appUserService.createUser(userDTO);

        // Assert the result
        assertNull(createdUser, "Created user should be null");
    }

}
