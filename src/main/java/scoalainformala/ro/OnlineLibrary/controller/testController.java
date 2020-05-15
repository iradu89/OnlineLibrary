package scoalainformala.ro.OnlineLibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class testController {

    @RequestMapping(value="/",method= GET)
    public String hello(){
        return "Hello Team 4 :) !!!";
    }
}
