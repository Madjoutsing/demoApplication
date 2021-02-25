package com.example.demo.controller;

import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembrePedagogieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Controller
    public class SimpleEmailController {

        @Autowired
        public JavaMailSender emailSender;

        @Autowired
        public EtudiantRepository etudiantRepository;

        @Autowired
        public MembrePedagogieRepository membrePedagogieRepository;

        public JavaMailSender javaMailSender;

       // @ResponseBody
        @RequestMapping(value = "/sendSimpleEmail/{email}", method = RequestMethod.GET)
        public String sendSimpleEmail(@PathVariable(name = "email" ) String email) throws MessagingException {
            MimeMessage message = emailSender.createMimeMessage();

            boolean multipart = true;

            MimeMessageHelper helper = new MimeMessageHelper(message,multipart,"utf-8");

            String htmlMsg = "Bonjour";

            message.setContent(htmlMsg,"text/html");

            helper.setTo(email);

            helper.setSubject("Mot de passe par défaut");

            this.emailSender.send(message);

            return "redirect:/ajoutEtudiantForm";

        }

    }

