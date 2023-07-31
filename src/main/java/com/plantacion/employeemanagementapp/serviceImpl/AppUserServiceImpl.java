package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import com.plantacion.employeemanagementapp.repository.RoleRepository;
import com.plantacion.employeemanagementapp.service.AppUserService;
import com.plantacion.employeemanagementapp.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public AppUser createUser(AppUserDTO user) {
        AppUser appUser = AppUser.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .role(roleService.findAllByIds(user.getUserRolesId()))
                .build();
        return repository.save(appUser);
    }

    @Override
    public Page<AppUser> getPagenatedUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByEmail(username);
        if (appUser.getEmail() == null){
            throw new UsernameNotFoundException("Incorrect login details");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        for(Role role : appUser.getRole()){
            roles.add(new SimpleGrantedAuthority(role.getTitle()));
        }
        return new User(appUser.getEmail(), appUser.getPassword(), roles);
    }
}
