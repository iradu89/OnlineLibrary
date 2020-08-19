package scoalainformala.ro.OnlineLibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user")
    public String homeUser() {
        return "Index user";
    }

    @GetMapping("/admin")
        public String homeAdmin() {
        return "Index Admin";
    }
}
