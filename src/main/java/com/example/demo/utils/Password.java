package com.example.demo.utils;

import com.example.demo.controller.AdminController;
import com.example.demo.modele.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class Password {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminController adminController;
    //encrypt password with bcryptpasswordencoder
    public static String encryptePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * methode prenannt en parametre une personne pour ensuite generer son pwd
     * @param p
     * @param role
     * @return
     */
    public String generate(Personne p, String role){
        Random rand = new Random();
        String nom=p.getNom().substring(0,1);
        String prenom=p.getPrenom().substring(0,1);
        String r= role.substring(0,2).toUpperCase();
        StringBuilder password = new StringBuilder(r + nom + prenom);
        for (int i=0 ;  i <4;i++){
            char c =(char) (rand.nextInt(32)+80);
            password.append(c);
        }
        System.out.println(password.toString());


        //String result = new SimpleEmailController().sendSimpleEmail(p, password.toString());
        System.out.println("etudiant2");
        //System.out.println("Message : " + result);
        return password.toString();

    }

//    public String generateEncrypt(String password){
//        return encryptePassword(password);
//    }




}
