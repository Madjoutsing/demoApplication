package com.example.demo.service.userRole;

import com.example.demo.modele.role.UserRole;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserRole enregistrerUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
