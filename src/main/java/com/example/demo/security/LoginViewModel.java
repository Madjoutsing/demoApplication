package com.example.demo.security;

/**
 * classe qui va contenir le login et le mdp de l'utilisateur
 * doit obligatoirement contenir ces deux attributs
 */


public class LoginViewModel {


    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
