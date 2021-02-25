package com.example.demo.controller;


import com.example.demo.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filiere")
public class FiliereController {
    @Autowired
    private FiliereRepository filiereRepository;
}
