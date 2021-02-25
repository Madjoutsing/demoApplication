package com.example.demo.service.appRole;

import com.example.demo.modele.role.AppRole;
import com.example.demo.modele.role.UserRole;
import com.example.demo.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AppRoleRepository appRoleRepository;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + "Where ur.appUser.userId = :userId";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public AppRole findByRoleName(String roleName) {
        return appRoleRepository.findByRoleName(roleName);
    }
}
