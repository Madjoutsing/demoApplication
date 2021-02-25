package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template="hello, %s!";
    private final AtomicLong counter=  new AtomicLong();

    @GetMapping("/greeting")
    public Greetings greetings(@RequestParam(name = "name", defaultValue = "World") String name){
        return new Greetings(counter.incrementAndGet(), String.format(template,name));
    }
}
