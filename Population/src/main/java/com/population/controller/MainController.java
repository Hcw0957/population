package com.population.controller;

import java.util.List;

import com.population.mapper.TollgateInfoMapper;
import com.population.service.EmergencyService;
import com.population.service.PopulationService;
import com.population.service.SpinRouteListService;
import com.population.service.TollgateInfoService;
import com.population.vo.EmergencyVO;
import com.population.vo.TollgateInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    EmergencyService e_service;
    @Autowired
    PopulationService service;
    @Autowired
    TollgateInfoService t_service;
    @Autowired
    SpinRouteListService s_service;



    @GetMapping("/")
    public String Main(Model model){
        return "/index";
    }
    @GetMapping("/road")
    public String getroad(Model model){
        return "/road/road";
    }
    @GetMapping("/sms")
    public String getsms(Model model){
        List<String> list = s_service.selectRouteAllName();
        model.addAttribute("list", list);
        return "/sms/timesms";
    }
    @GetMapping("/construt")
    public String getconstrut(Model model){
        
        return "/construct/construct";
    }

    @GetMapping("/time")
    public String getTime(Model model){
        return "/time/time";
    }
    @GetMapping("/toll")
    public String getToll(Model model){
        List<TollgateInfoVO> list = t_service.selectRouteNames();
        model.addAttribute("route", list);
        // System.out.println(list);
        // model.addAttribute("tollgates", toll_mapper.selectRouteNames());
        return "/tollgate/tollgate";
    }
    @GetMapping("/resting")
    public String getRest(){
        return "/rest/rest";
    }
    @GetMapping("/emer")
    public String getEme(){
        return "/emer/emer";
    }

    @GetMapping("/route")
    public String getroute(Model model){

        // List<String> list = s_service.selectRouteAllName();
        // model.addAttribute("list", list);
        return "/sms/route";
    }
}

