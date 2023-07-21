package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.RoleDTO;
import com.plantacion.employeemanagementapp.repository.RoleRepository;
import com.plantacion.employeemanagementapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> fetchAllRoles() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findbyId(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Role> findAllByIds(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    public void addRole(RoleDTO roleDTO){
        Role role = new Role();
        role.setTitle(roleDTO.getTitle());
        repository.save(role);
    }

}
