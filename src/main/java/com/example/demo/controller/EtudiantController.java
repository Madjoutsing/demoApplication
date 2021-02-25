package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.modele.*;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.service.Cycle.CycleIService;
import com.example.demo.service.Filiere.FiliereIService;
import com.example.demo.service.Option.OptionIService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired // instancier et initialiser un objet
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CycleIService cycleService;

    @Autowired
    private FiliereIService filiereService;

    @Autowired
    private OptionIService optionService;



//    @GetMapping("/admin/createEtudiants")
//    public String showCreateForm(Model model) {
//        EtudiantDTO etudiantsForm = new EtudiantDTO();
//
//        for (int i = 1; i <=10; i++){
//            etudiantsForm.addEtudiant(new Etudiant());
//        }
//
//        model.addAttribute("form", etudiantsForm);
//        return "createEtudiantsForm";
//    }

/*    @PostMapping("/admin/enregistrerEtudiants")
    public String saveEtudiants(@ModelAttribute EtudiantDTO form, Model model) {

        etudiantRepository.saveAll(form.getEtudiant());
        model.addAttribute("etudiants", etudiantRepository.findAll());
        return "redirect:/etudiant/getAllEtudiants";
    }*/






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
}

