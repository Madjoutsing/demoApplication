package com.example.demo.controller;


import com.example.demo.File.FileSystemStorageService;
import com.example.demo.dto.DocumentDTO;
import com.example.demo.dto.MembrePedagogieDTO;
import com.example.demo.modele.Document;
import com.example.demo.modele.MembrePedagogie;
import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.MembrePedagogieRepository;
import com.example.demo.service.Document.DocServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/membrepedagogie")
public class MembrePedagogieController {
    @Autowired
    private MembrePedagogieRepository membrePedagogieRepository;

    @Autowired
    private DocServiceImpl docService;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllMembrePedagogies")
    public String ListeDesMembrePedagogies(Model model) {

        List<MembrePedagogie> membrepedagogieList=membrePedagogieRepository.findAll();
        model.addAttribute("util", membrepedagogieList);

        return "listeMembrePedagogies";
    }

    @GetMapping("/getAllDocuments")
    public String ListeDesDocuments(Model model) {

        List<Document> documentList=docService.allDocuments(); //appel de la fonction qui retourne la liste
        model.addAttribute("util", documentList); // dans notre objet, on met les véhicules enregistrés dans la BD
        model.addAttribute("documentDTO",new DocumentDTO());

        return "ListFiche";
    }

    @GetMapping("/ajoutDocumentForm")
    public String test(Model model){
        model.addAttribute("documentDTO", new DocumentDTO());
        return "ajoutFiche";
    }

    @PostMapping("/saveadd")
    public String Savetest(@RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            double size = (double) file.getSize()/1024;//taille en bytes il faut par 1024 puis arrondir en utilisant la librairie Math
            new DocumentDTO().setPathFile((String) fileSystemStorageService.store(file).getBody());
            System.out.println(8);
        }


        return "redirect:/ajoutDocumentForm";
    }
    @PostMapping("/createDocuments")
    public String showCreateForm(@Validated DocumentDTO documentDTO,
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
        if(!file.isEmpty()){
            double size = (double) file.getSize()/1024;//taille en bytes il faut par 1024 puis arrondir en utilisant la librairie Math
            documentDTO.setPathFile((String) fileSystemStorageService.store(file).getBody());
            documentDTO.setTaille((long)size);
            System.out.println(8);
        }
        System.out.println(documentDTO.getTitre());
        documentDTO.setDate(LocalDateTime.now());
        documentDTO.setVersion("1");

        //abscence de spring security
        AppUser appUser = appUserRepository.findByUserName(authentication.getName());

//        try {
//            if (etudiant.getDocuments() == null) {
//                Files.copy(file.getInputStream(), this.root);
//                documentDTO.setPathFile(file.getOriginalFilename());
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        Document document = documentDtoToEntity(documentDTO);
        document.setEtudiant(appUser.getEtudiant());
        docService.saveAll(document);




//        if (appUser.getRole() == "ROLE_etudiant") {
//            return "redirect:/etudiant/ajoutDocumentForm";
//
//        }
//        else {
//            if(appUser.getRole() == "ROLE_mp") {
//                return "redirect:/membrepedagogie/ajout";
//            }
//        }
        return "redirect:/membrepedagogie/ajoutDocumentForm";

    }

//    @GetMapping("/admin/createMembrepedagogies")
//    public String showCreateForm(Model model) {
//
//    }

    @PostMapping("/admin/enregistrerMembrePedagogies")
    public String saveMembrePedagogies(@ModelAttribute MembrePedagogieDTO form, Model model) {

        membrePedagogieRepository.saveAll(form.getMembrepedagogies());
        model.addAttribute("membrepedagogies", membrePedagogieRepository.findAll());
        return "redirect:/membrepedagogie/getAllMembrePedagogies";
    }

//    @GetMapping("/admin/editMembrePedagogies")
//    public String showEditForm(Model model) {
//        List<MembrePedagogie> membrePedagogies = new ArrayList<>();
//        membrePedagogieRepository.findAll().iterator().forEachRemaining(membrePedagogies::add);
//
//        model.addAttribute("form", new MembrePedagogieDTO(membrePedagogies));
//        return "editUsersForm";
//    }

    @GetMapping("/admin/delete/{id}")
    public String deleteMembrePedagogie (Model model,@PathVariable(name = "id") String id){
        membrePedagogieRepository.deleteById(id);
        model.addAttribute("etudiants", membrePedagogieRepository.findAll());
        return "listeMembrePedagogies";
    }

    private DocumentDTO documentToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }

    private Document documentDtoToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
