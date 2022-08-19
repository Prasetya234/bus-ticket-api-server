package com.bus.ticket.enggine.configure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String homePage() {
        return "Start Tiket Bus BackEnd Aplication At " + new Date();
    }
}
