package com.example.demo.service;


import com.example.demo.modele.role.AppUser;
import com.example.demo.service.appRole.AppRoleServiceImpl;
import com.example.demo.service.appUser.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private AppRoleServiceImpl appRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AppUser appUser = this.appUserService.findUserAccount(userName);

        if (appUser == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("found user " + appUser);

        //[ROLE_USER, ROLE_ADMIN,...]
        List<String> roleNames = this.appRoleService.getRoleNames(appUser.getUserId());
        System.out.println(roleNames);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
             for (String role : roleNames) {
                 //ROLE_USER, ROLE_ADMIN, ...
                 GrantedAuthority authority = new SimpleGrantedAuthority(role);
                 grantList.add(authority);
             }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), appUser.getEncryptedPassword(), grantList);

        return userDetails;
    }
}
