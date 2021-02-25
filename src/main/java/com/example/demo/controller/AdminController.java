package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.modele.*;
import com.example.demo.modele.role.AppUser;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembrePedagogieRepository;
import com.example.demo.service.Cycle.CycleServiceImpl;
import com.example.demo.service.Etudiant.EtudiantServiceImpl;
import com.example.demo.service.Filiere.FiliereServiceImpl;
import com.example.demo.service.MembrePedagogie.MPServiceImpl;
import com.example.demo.service.Option.OptionServiceImpl;
import com.example.demo.service.appRole.AppRoleServiceImpl;
import com.example.demo.service.appUser.AppUserServiceImpl;
import com.example.demo.service.userRole.UserRoleServiceImpl;
import com.example.demo.utils.Password;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private AppRoleServiceImpl appRoleService;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private CycleServiceImpl cycleService;

    @Autowired
    private OptionServiceImpl optionService;

    @Autowired
    private FiliereServiceImpl filiereService;

    @Autowired
    private MPServiceImpl mpService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public EtudiantRepository etudiantRepository;

    @Autowired
    public MembrePedagogieRepository membrePedagogieRepository;

    public JavaMailSender javaMailSender;


    @GetMapping("/ajoutEtudiantForm")
    public String formEtudiant(Model model) {
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        model.addAttribute("etudiantDTO", etudiantDTO);

        List<Cycle> cycles = cycleService.listCycles();
        List<CycleDTO> cycleDTOS = cycles.stream().map(this::cycleToDto).collect(Collectors.toList());
        model.addAttribute("cycleDTOS", cycleDTOS);
        System.out.println(cycleDTOS);

        List<OptionFiliere> options = optionService.listOptions();
        List<OptionFiliereDTO> optionFiliereDTOS = options.stream().map(this::optionToDto).collect(Collectors.toList());
        model.addAttribute("optionFiliereDTOS", optionFiliereDTOS);
        System.out.println((optionFiliereDTOS));

        List<Filiere> filieres = filiereService.listFilieres();
        List<FiliereDTO> filiereDTOS = filieres.stream().map(this::filiereToDto).collect(Collectors.toList());
        model.addAttribute("filiereDTOS", filiereDTOS);
        model.addAttribute("documentDTO",new DocumentDTO());
        System.out.println((filiereDTOS));

        return "ajoutEtudiant";
    }

    @Secured(value = {"ROLE_admin"})
    @PostMapping("/saveEtudiant")
    public String saveEtudiant(@Validated EtudiantDTO etudiantDTO, BindingResult result) throws MessagingException {
        //faire les vérifications qu'il faut sur les attributs de Etudiant

        if (etudiantService.findBymail(etudiantDTO.getMail()) != null || etudiantService.findBynumero(etudiantDTO.getNumero()) != null) {
            return "ajoutEtudiant";
        }

        Etudiant etudiant = etudiantDtoToEntity(etudiantDTO);
        System.out.println(etudiant);
        System.out.println("etudiant");
        Etudiant e = etudiantService.enregistrerEtudiant(etudiant);


        String password = new Password().generate(etudiant, "ETUDIANT");
        String mailEtudiant = etudiant.getMail();



        envoyerLeMotDePasseParDefaut(mailEtudiant,password);
        System.out.println(password);

        AppUser user = new AppUser(etudiant.getMail(), passwordEncoder.encode(password),  "ETUDIANT", true, e);
        user = appUserService.enregistrerAppuser(user);


        return "redirect:/admin/ajoutEtudiantForm";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/getAllEtudiants") //getAllEtudiants est ce qu'on met dans l'url
    public String ListeDesEtudiants(Model model) {

        List<Etudiant> etudiantList = etudiantRepository.findAll(); //appel de la fonction qui retourne la liste
        model.addAttribute("util", etudiantList); // dans notre objet, on met les véhicules enregistrés dans la BD
        model.addAttribute("documentDTO",new DocumentDTO());
        return "ListEtudiant";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/editEtudiants/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        Etudiant etudiant = etudiantRepository.rechercherById(id);
        System.out.println(etudiant.toString());
        model.addAttribute("etudiantDTO", etudiant);
        model.addAttribute("documentDTO",new DocumentDTO());


        List<Cycle> cycles = cycleService.listCycles();
        List<CycleDTO> cycleDTOS = cycles.stream().map(this::cycleToDto).collect(Collectors.toList());
        model.addAttribute("cycleDTOS", cycleDTOS);
        System.out.println(cycleDTOS);

        List<OptionFiliere> options = optionService.listOptions();
        List<OptionFiliereDTO> optionFiliereDTOS = options.stream().map(this::optionToDto).collect(Collectors.toList());
        model.addAttribute("optionFiliereDTOS", optionFiliereDTOS);
        System.out.println((optionFiliereDTOS));

        List<Filiere> filieres = filiereService.listFilieres();
        List<FiliereDTO> filiereDTOS = filieres.stream().map(this::filiereToDto).collect(Collectors.toList());
        model.addAttribute("filiereDTOS", filiereDTOS);
        System.out.println((filiereDTOS));

        return "editUsersForm";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/delete/{id}")
    public String deleteEtudiant(Model model, @PathVariable(name = "id") Long id) {
        System.out.println(id);
        etudiantRepository.deleteById(id);
        model.addAttribute("documentDTO",new DocumentDTO());
        System.out.println(id+id);
        return "redirect:/admin/getAllEtudiants";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/ajoutMPform")
    public String formMP (Model model) {
        MembrePedagogie membrePedagogie = new MembrePedagogie();
        model.addAttribute("membrePedagogie", membrePedagogie);
        model.addAttribute("documentDTO",new DocumentDTO());
        return "AjoutMP";
    }

    @Secured(value = {"ROLE_admin"})
    @PostMapping("/saveMP")
    public String saveMP(@Validated MembrePedagogieDTO membrePedagogieDTO, BindingResult result) throws MessagingException {
        if (mpService.findBymail(membrePedagogieDTO.getMail()) != null || mpService.findBynumero(membrePedagogieDTO.getNumero()) != null) {

            return "ajoutMP";
        }
        MembrePedagogie membrePedagogie = membrePedagogieDtoToEntity(membrePedagogieDTO);
        System.out.println(membrePedagogie);
       MembrePedagogie m = mpService.enregistrerMembrePedagogie(membrePedagogie);

        String mailMembrepedagogie = membrePedagogie.getMail();

        String password = new Password().generate(membrePedagogie, "MP");

        envoyerLeMotDePasseParDefaut(mailMembrepedagogie,password);



        AppUser user = new AppUser(membrePedagogie.getMail(), passwordEncoder.encode(password),  true, "MP", m);
        user = appUserService.enregistrerAppuser(user);

        return "redirect:/admin/ajoutMPform";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/getAllMembrePedagogies") //getAllEtudiants est ce qu'on met dans l'url
    public String ListeDesMembrePedagogies(Model model) {

        List<MembrePedagogie> membrePedagogieList = membrePedagogieRepository.findAll(); //appel de la fonction qui retourne la liste
        model.addAttribute("util", membrePedagogieList); // dans notre objet, on met les véhicules enregistrés dans la BD
        model.addAttribute("document",new Document());
        return "ListMP";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/editMembrePedagogies/{id}")
    public String showEditFormMP(Model model, @PathVariable(name = "id") Long id) {
        MembrePedagogie membrePedagogie = membrePedagogieRepository.rechercherById(id);
        System.out.println(membrePedagogie.toString());
        model.addAttribute("membrepedagogieDTO", membrePedagogie);
        model.addAttribute("documentDTO",new DocumentDTO());
        return "editUsersForm1";
    }

    @Secured(value = {"ROLE_admin"})
    @GetMapping("/deletemp/{id}")
    public String deleteMembrePedagogie(Model model, @PathVariable(name = "id") Long id) {
        System.out.println(id);
        membrePedagogieRepository.deleteById(id);
        System.out.println(id+id);
        model.addAttribute("documentDTO",new DocumentDTO());
        return "redirect:/admin/getAllMembrePedagogies";
    }

    private EtudiantDTO etudiantToDto(Etudiant etudiant) {
        return modelMapper.map(etudiant, EtudiantDTO.class);
    }

    private Etudiant etudiantDtoToEntity(EtudiantDTO etudiantDTO) {
        return modelMapper.map(etudiantDTO, Etudiant.class);
    }

    private MembrePedagogieDTO membrepedagogieToDto(MembrePedagogie membrePedagogie) { return modelMapper.map(membrePedagogie, MembrePedagogieDTO.class);}

    private MembrePedagogie membrePedagogieDtoToEntity(MembrePedagogieDTO membrePedagogieDTO) {
        return modelMapper.map(membrePedagogieDTO, MembrePedagogie.class);
    }

    private CycleDTO cycleToDto(Cycle cycle) {
        return modelMapper.map(cycle, CycleDTO.class);
    }

    private Cycle cycleDtoToEntity(CycleDTO cycleDTO) {
        return modelMapper.map(cycleDTO, Cycle.class);
    }

    private OptionFiliereDTO optionToDto(OptionFiliere optionFiliere) { return modelMapper.map(optionFiliere, OptionFiliereDTO.class); }

    private OptionFiliere optionDtoToEntity(OptionFiliereDTO optionDTO) { return modelMapper.map(optionDTO, OptionFiliere.class);}

    private FiliereDTO filiereToDto(Filiere filiere) { return  modelMapper.map(filiere, FiliereDTO.class); }

    private Filiere filiereDtoToEntity(FiliereDTO filiereDTO) { return modelMapper.map(filiereDTO, Filiere.class);}

    public void envoyerLeMotDePasseParDefaut(String mail, String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message,multipart,"utf-8");

        String htmlMsg = "Bonjour, <br> Votre mot de passe par défaut est "+ password;

        message.setContent(htmlMsg,"text/html");

        helper.setTo(mail);

        helper.setSubject("Mot de passe par défaut");

        this.emailSender.send(message);

    }
}
