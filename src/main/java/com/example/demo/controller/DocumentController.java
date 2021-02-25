package com.example.demo.controller;


import com.example.demo.File.FileSystemStorageRestController;
import com.example.demo.File.FileSystemStorageService;
import com.example.demo.dto.DocumentDTO;
import com.example.demo.modele.Document;
import com.example.demo.modele.ElasticSearch.ModeleDoc;
import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.ElasticSearch.RepositoryDoc;
import com.example.demo.repository.EtudiantRepository;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/etudiant")
public class DocumentController {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Autowired
    private FileSystemStorageRestController fileSystemStorageRestController;

    @Autowired
    private DocServiceImpl docService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RepositoryDoc repositoryDoc;

    @Autowired
    private MembrePedagogieRepository membrePedagogieRepository;

//    @Autowired
//    private DocumentRepository documentRepository;

    private final Path root = Paths.get("upload-dir");

    @GetMapping("/getAllDocuments")
    public String ListeDesDocuments(Model model) {

        List<Document> documentList=docService.allDocuments(); //appel de la fonction qui retourne la liste
        model.addAttribute("util", documentList); // dans notre objet, on met les véhicules enregistrés dans la BD
        model.addAttribute("documentDTO",new DocumentDTO());

        return "ListRapports";
    }


    @GetMapping("/testadd")
    public String test(){
        return "testdocajout";
    }

    @GetMapping("/ajoutDocumentForm")
    public String test(Model model){
        model.addAttribute("documentDTO", new DocumentDTO());
        return "ajoutRapport";
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
        return "redirect:/etudiant/ajoutDocumentForm";

    }


//    @PostMapping("/affiche/{pathFile}")
//    public void affiche(@PathVariable(name = "pathFile") String pathFile) throws Exception {
//        fileSystemStorageRestController.serveFileCatalogue(pathFile);
//
//    }
//    @GetMapping("/editDocuments/{id}")
//    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
//        Document document = documentRepository.findById(id);
//        System.out.println(document.toString());
//        model.addAttribute("documentDTO", document);
//        return "editDocuments";
//    }


    @GetMapping("/delete/{idDoc}")
    public String deleteDocument(Model model, @PathVariable(name = "idDoc") String idDoc) {
        System.out.println(idDoc);
        docService.deleteById(Long.parseLong(idDoc));
        System.out.println(idDoc+idDoc);
        model.addAttribute("documentDTO",new DocumentDTO());
        return "redirect:/etudiant/getAllDocuments";
    }

    @GetMapping("/editDocuments/{idDoc}")
    public String showEditFormDocs(Model model, @PathVariable(name = "idDoc") String idDoc) {
        Document document = (Document) documentRepository.findByIdDoc(Long.parseLong(idDoc));
        System.out.println(document.toString());
        model.addAttribute("documentDTO", document);
        return "editDoc";
    }

    @GetMapping("/findDocument")
    public String showFindForm(Model model,@RequestParam (value = "theme") String theme ) {
        List<Document> documentList = documentRepository.findByTheme(theme);
        System.out.println(theme);
        model.addAttribute("util", documentList);
        model.addAttribute("documentDTO",new DocumentDTO());

        return "ListRapports";
    }

    @GetMapping ("/search/{motCle}")
    public String rechercheDocument(Model model, @ModelAttribute ModeleDoc modeleDoc, @PathVariable(name = "motCle") String motCle) {
        System.out.println(motCle);
        List<Document> documents = docService.findByMotCle(motCle);

       // List<ModeleDoc> docList = docService.search(modeleDoc.getSearch());
        model.addAttribute("util", documents);
        model.addAttribute("documentDTO",new DocumentDTO());
        return "ListRapports";
    }
//
//    @GetMapping ("/search/{search}")
//    public String rechercheDocument1(Model model, @ModelAttribute ModeleDoc modeleDoc,  @PathVariable(name = "search") String search) {
//        List<ModeleDoc> modeleDocs = repositoryDoc.findAllBySearch(search);
//
//        // List<ModeleDoc> docList = docService.search(modeleDoc.getSearch());
//        model.addAttribute("util", modeleDocs);
//        model.addAttribute("documentDTO",new DocumentDTO());
//        return "ListRapports";
//    }



//    @PostMapping("/enregistrerDocuments")
//    public String saveDocuments(@ModelAttribute DocumentDTO form, Model model) {
//
//        docService.saveAll(form.getDocuments());
//        model.addAttribute("documents", docService.findAll());
//        return "redirect:/document/getAllDocuments";
//    }

//    @GetMapping("/editDocuments")
//    public String showEditForm(Model model) {
//        List<Document> documents = new ArrayList<>();
//        docService.findAll().iterator().forEachRemaining(documents::add);
//
//        model.addAttribute("form", new DocumentDTO(documents));
//        return "editDocumentsForm";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteDocument (Model model,@PathVariable(name = "id") String id){
//        docService.deleteById(id);
//        model.addAttribute("documents", docService.findAll());
//        return "listeDocuments";
//    }

    private DocumentDTO documentToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }

    private Document documentDtoToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
