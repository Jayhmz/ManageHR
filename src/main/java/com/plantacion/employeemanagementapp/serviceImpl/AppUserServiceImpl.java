package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.model.dto.RoleDTO;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import com.plantacion.employeemanagementapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void createUser(AppUserDTO user) {
        AppUser appUser = AppUser.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build();

        List<Role> roles = new ArrayList<>();
        for(RoleDTO role : user.getRoleDTO()){
            //roles.add(new Role(role.getId()));
            roles.add(new Role(2L));
            roles.add(new Role(1L));
        }
        appUser.setRole(roles);
        repository.save(appUser);
    }
}
