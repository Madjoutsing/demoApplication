package com.example.demo.service.userRole;

import com.example.demo.modele.role.UserRole;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {

    public UserRole enregistrerUserRole(UserRole userRole);
}
