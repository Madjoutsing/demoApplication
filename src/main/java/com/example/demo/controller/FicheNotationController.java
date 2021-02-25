package com.example.demo.controller;

import com.example.demo.File.FileSystemStorageService;
import com.example.demo.dto.FicheNotationDTO;
import com.example.demo.modele.FicheNotation;
import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.FicheNotationRepository;
import com.example.demo.service.FicheNotation.FicheNotationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/fnc")
public class FicheNotationController {

    @Autowired
    private FicheNotationServiceImpl ficheNotationService;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    private @Autowired
    AppUserRepository appUserRepository;

    private @Autowired
    ModelMapper modelMapper;

    private @Autowired
    FicheNotationRepository ficheNotationRepository;


    @GetMapping("/getAllFiches")
    public String ListeDesFiches(Model model) {

        List<FicheNotation> ficheNotationList = ficheNotationService.findAll(); //appel de la fonction qui retourne la liste
        model.addAttribute("util", ficheNotationList); // dans notre objet, on met les véhicules enregistrés dans la BD
        model.addAttribute("fichenotationDTO", new FicheNotationDTO());

        return "ListFiche";
    }

    @GetMapping("/ajoutFicheForm")
    public String test(Model model) {
        model.addAttribute("fichenotationDTO", new FicheNotationDTO());
        return "ajoutFiche";
    }

    @PostMapping("/saveadd1")
    public String Savetest(@RequestParam("file") MultipartFile file) throws IOException {
//        if (!file.isEmpty()) {
//            double size = (double) file.getSize() / 1024;//taille en bytes il faut par 1024 puis arrondir en utilisant la librairie Math
//            new DocumentDTO().setPathFile((String) fileSystemStorageService.store(file).getBody());
//            System.out.println(8);
//        }


        return "redirect:/ajoutFicheForm";
    }

    @PostMapping("/createFiches")
    public void test(){
        System.out.println("test");
    }


    @PostMapping("/createFiches1")
    public String showCreateForm(@Validated FicheNotationDTO ficheNotationDTO,@AuthenticationPrincipal UserDetails currentUser,
                                 Authentication authentication, BindingResult result, @RequestParam("file") MultipartFile file) throws IOException {


//            Document document = new Document();
//
//            document.setTitre(documentDTO.getTitre());
//            document.setTheme(documentDTO.getTheme());
//            document.setEtat(documentDTO.getEtat());
//            document.setNomEntreprise(documentDTO.getNomEntreprise());
//            document.setMotCle(documentDTO.getMotCle());
//            document.setDateScolarite(documentDTO.getDateScolarite());
//            document.setDateSoutenance(documentDTO.getDateSoutenance());
//            document.setPathFile(documentDTO.getPathFile());
//
//        //abscence de spring security
//        long l = 1;
//        Etudiant etudiant = etudiantRepository.findById(1);
//
//        document.setEtudiant(etudiant);
//
//        documentRepository.save(document);

        if (result.hasErrors())
            return "error";
        if (file == null) {
            return "pas de fichier";
        }
        if (!file.isEmpty()) {
            double size = (double) file.getSize() / 1024;//taille en bytes il faut par 1024 puis arrondir en utilisant la librairie Math
            ficheNotationDTO.setPathFile((String) fileSystemStorageService.store(file).getBody());
//            documentDTO.setTaille((long)size);
//            System.out.println(8);
        }
//        System.out.println(documentDTO.getTitre());
//        documentDTO.setDate(LocalDateTime.now());
//        documentDTO.setVersion("1");

            //abscence de spring security
        System.out.println("test");
        System.out.println(authentication.getName()+"moi");
        System.out.println(authentication.getName());
        System.out.println(authentication.isAuthenticated());
            AppUser appUser = appUserRepository.findByUserName(authentication.getName());

//        try {
//            if (etudiant.getDocuments() == null) {
//                Files.copy(file.getInputStream(), this.root);
//                documentDTO.setPathFile(file.getOriginalFilename());
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        FicheNotation ficheNotation = ficheToDto(ficheNotationDTO);
        ficheNotation.setMembrepedagogie(appUser.getMembrePedagogie());
        ficheNotationService.saveFiche(ficheNotation);



//        if (appUser.getRole() == "ROLE_etudiant") {
//            return "redirect:/etudiant/ajoutDocumentForm";
//
//        }
//        else {
//            if(appUser.getRole() == "ROLE_mp") {
//                return "redirect:/membrepedagogie/ajout";
//            }
//        }
            return "redirect:/membrepedagogie/ajoutFicheForm";

        }

        private FicheNotation ficheToDto(FicheNotationDTO ficheNotationDTO) {
            return modelMapper.map(ficheNotationDTO, FicheNotation.class);
        }
//
//        private Document documentDtoToEntity(DocumentDTO documentDTO) {
//            return modelMapper.map(documentDTO, Document.class);
//        }
    }



