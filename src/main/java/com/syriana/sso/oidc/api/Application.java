package com.syriana.sso.oidc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(
        scanBasePackages = {"com.syriana.sso.oidc.api"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    @RequestMapping("/test")
    class AppController {

        @GetMapping("/hello")
        String hello() {
            return "12315";
        }
    }
}
