package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.repository.RoleRepository;
import com.plantacion.employeemanagementapp.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @InjectMocks
    private RoleServiceImpl roleService;
    @Mock
    private RoleRepository roleRepository;
    private Role role1;
    private Role role2;

    @BeforeEach
    void beforeEach(){
        role1 = new Role(1, "CREATE");
        role2 = new Role(2, "WRITE");
    }
    @Test
    void fetchAllRoles() {
        //mock the invocation of findAllById
        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));
        //call the method under test
        List<Role> roles = roleService.fetchAllRoles();
        //verify the number of calls
        verify(roleRepository, times(1)).findAll();
        //assert results
        assertEquals(2, roles.size());
        assertNotNull(roles);
        assertEquals(role1, roles.get(0));
        assertEquals(role2, roles.get(1));
    }

    @Test
    void findbyId() {
        //mock the invocation of findById
        when(roleRepository.findById(1)).thenReturn(Optional.of(role1));
        //call the method under test
        Optional<Role> role = roleService.findbyId(1);
        //assert the result
        assertEquals(role1, role.get());
        assertNotNull(role);
    }

    @Test
    void findAllByIds() {
    }
}