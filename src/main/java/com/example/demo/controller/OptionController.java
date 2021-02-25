package com.example.demo.controller;


import com.example.demo.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/option")
public class OptionController {
    @Autowired
    private OptionRepository optionRepository;
}
