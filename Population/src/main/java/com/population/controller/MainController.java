package com.population.controller;

import com.population.service.PopulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    PopulationService service;
    @GetMapping("/")
    public String Main(Model model){
        return "/index";
    }
    @GetMapping("/road")
    public String getroad(Model model){
        return "/road/road";
    }
    @GetMapping("/construt")
    public String getconstrut(Model model){
        
        return "/construct/construct";
    }
    @GetMapping("/exit")
    public String getexit(Model model){
        return "/exit/exit";
    }
}
