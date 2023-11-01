package uz.architect.springsecurityexample.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public HttpEntity<?> userPage(Principal principal) {
        return ResponseEntity.ok("Welcome to User page %s".formatted(principal.getName()));
    }
}
