package com.example.demo.security;

/**
 * classe utilisée pour définir des constantes pour JWT
 */
public class SecurityProperties {
    public static final String SECRET = "MALIGAHSTARTAPP2020";
    public static final int TEMPS_EXPIRATION = 12*60*60*100;//en millisecondes ceci équivaut
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_EXPIRATION="expired_date";
    public static String location = "upload-dir";

   //public static final String BASE_URL="http://localhost:8085/";
    //public static final String BASE_URL="http://localhost:8080/";
    //public static final String BASE_URL="http://localhost:8000/app-1/";
    public static final String BASE_URL="http://82.165.242.115:8080/maligah-1/";
    public static final String URL_LOGIN = BASE_URL + "login";
    public static final String API_BASE_URL = BASE_URL + "api/";
    public static final String mailURL = "noreply@maligah.com";



}
