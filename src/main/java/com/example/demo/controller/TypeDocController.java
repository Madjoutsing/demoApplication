package com.example.demo.controller;

import com.example.demo.repository.TypeDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typedoc")
public class TypeDocController {
    @Autowired
    private TypeDocRepository typeDocRepository;
}
