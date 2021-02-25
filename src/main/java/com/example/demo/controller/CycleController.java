package com.example.demo.controller;


import com.example.demo.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cycle")
public class CycleController {
    @Autowired
    private CycleRepository cycleRepository;
}
