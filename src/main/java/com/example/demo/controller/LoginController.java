package com.example.demo.controller;


import com.example.demo.dto.DocumentDTO;
import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.LoginViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

    @Autowired
            private AppUserRepository appUserRepository;

    RestTemplate restTemplate = new RestTemplate();
    @GetMapping(value = {"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("loginViewModel",new LoginViewModel());
        return "loginPage";
    }

    @GetMapping(value = {"/logout"})
    public String logoutPage(Model model) {
        return "login";
    }

    @GetMapping(value = {"/index"})
    public String idex(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        AppUser appUser = appUserRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("connexion",appUser);
        model.addAttribute("documentDTO",new DocumentDTO());

        return "index";
    }


    @GetMapping("/success")
    public String redirectPage(Model model, @CookieValue String username,@CookieValue String role) {
        if(username == null){
            return "redirect:/login";
        }

        model.addAttribute("username",username);
        model.addAttribute("role",role);
        model.addAttribute("documentDTO",new DocumentDTO());
        /*Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.toString());*/
        return "successPage";
    }



//    @PostMapping("/verification")
//    public String verification(@ModelAttribute LoginViewModel loginViewModel, HttpServletResponse response) {
//        try {
//            String url = "http://localhost:8088/login";
//            //pour le déploiement Utiliser un fichier pour ce param est préférable
//            //String url = "http://34.121.63.60:8080/app-1/login";
//
//            //Cette objet permet de définir le body de la requête http
//            HttpEntity<LoginViewModel> request = new HttpEntity<>(loginViewModel);
//
//
//                /*
//                Ici on exécute la requête vers le service dans le but de récupérer
//                la réponse entière(body, headers)
//                Un objet LoginViewModel doit normalement se trouver dans le body de la reponse
//                en fonction de cet instruction
//                Mais il ne le sera pas. Je ne sais pas comment mettre le type retour à void
//                 */
//            ResponseEntity<AppUser> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
//
//                /*
//                    À ce niveau, la requête au service a réussi, le code est 200 Ok
//                    on a réussi à authentifier l'utilisateur
//                    on récupère l'ensemble des headers
//                 */
//            List<String> headerValues = responseEntity.getHeaders().get(SecurityProperties.HEADER_STRING);
//
//            //je récupère le token
//            String token = headerValues.get(0);
//
//            //un token se présente ainsi Bearer esrsf..... // Donc je sépare le mot clé Bearer du reste du token
//            String[] bearerValue = token.split(" ");
//
//            //je crée un cookie qui sera sauvegardé au niveau du navigateur
//
//            //Cookie cookieNavigateur = new Cookie("token", bearerValue[1]);
//            Cookie cookieUser = new Cookie("username",responseEntity.getBody().getUserName());
//            Cookie cookieRole = new Cookie("role",responseEntity.getBody().getRole());
//
//                /*on définit la période de validité du token 5 jours dans le cas présent
//                Même période que celle d'un token
//                 */
//            // cookieNavigateur.setMaxAge(5 * 24 * 60 * 60); 10 min temps en secondes
//            //cookieNavigateur.setMaxAge(5 * 24*100);
//            cookieUser.setMaxAge(5 * 24*100);
//            cookieRole.setMaxAge(5 * 24*100);
//
//                /*Ceci pour éviter les attaques XSS, en fait on pourra pas récupérer le cookie
//                à partir d'un fichier js
//                 */
//            //cookieNavigateur.setHttpOnly(true);
//            cookieUser.setHttpOnly(true);
//            cookieRole.setHttpOnly(true);
//            //cookieNavigateur.setPath("/");
//            cookieUser.setPath("/");
//            cookieRole.setPath("/");
//            //cookie.setSecure(true); Ceci pour signifier que le cookie ne fonctionnera qu'en https
//
//            //je passe le cookie dans la réponse. Ceci va le sauvegarder dans le navigateur
//            //response.addCookie(cookieNavigateur);
//            response.addCookie(cookieUser);
//            response.addCookie(cookieRole);
//
//            //je redirige l'utilisateur vers la page d'acceuil
//            //Pour l'admin, je dois rediriger vers sa page d'accueil
//            if (responseEntity.getBody().getRole().equals("ADMIN")){
//                return "redirect:/success";
//            }
//            //Pour l'étudiant, faire la meme chose.
//            if (responseEntity.getBody().getRole().equals("ETUDIANT")){
//                return "redirect:/success";
//            }
//            //Pour le MP, faire pareil
//            if (responseEntity.getBody().getRole().equals("MP")){
//                return "redirect:/success";
//            }
//
//            return "redirect:/success";
//        } catch (HttpClientErrorException e) {
//            //Ici On a pas authentifié l'utilisateur, login ou mdp invalide
//            //model.addAttribute("loginViewModel",new LoginViewModel());
//            //model.addAttribute("message","Ce champ est invalide");
//            return "loginPage"; //page login avec authentif
//        }
//    }
}
