package com.example.jwttesttask.service;

import com.example.jwttesttask.domain.Roles;
import com.example.jwttesttask.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RolesRepository rolesRepository;

    public Roles getUserRole(){
        return rolesRepository.findByName("ROLE_USER").get();
    }
}
