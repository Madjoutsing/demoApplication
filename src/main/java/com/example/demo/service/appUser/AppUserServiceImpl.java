package com.example.demo.service.appUser;

import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from "+AppUser.class.getName()+" e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public AppUser enregistrerAppuser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
