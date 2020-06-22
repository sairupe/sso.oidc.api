package com.syriana.sso.oidc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@SpringBootApplication
public class Application {

    @Bean
    WebClient client(ClientRegistrationRepository clieRegRepo, OAuth2AuthorizedClientRepository authCliRepo) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction ifunc =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clieRegRepo, authCliRepo);
        ifunc.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder().baseUrl("http://localhost:8081/resources")
                .apply(ifunc.oauth2Configuration())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    @RequestMapping("/")
    class AppController {

        @Autowired
        private WebClient webClient;

        @GetMapping
        String hello() {
            return "12315";
        }

        @GetMapping("/claims")
        Map getClaims() {
            return webClient.get().uri("claims").retrieve().bodyToMono(Map.class)
                    .block();
        }
    }
}
