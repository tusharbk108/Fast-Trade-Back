package com.FastTrade.controller;

import com.FastTrade.Model.Company;
import com.FastTrade.service.homeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class homeController {

    @Autowired
    homeService homeService;

    @GetMapping("/home")
    @ResponseBody
    List<List<String>> home(){
      return   homeService.home();
    }

    @GetMapping("/details")
    @ResponseBody
    List<List<String>> home(@RequestParam String symbol){
        return   homeService.getDetails(symbol);
    }

}
