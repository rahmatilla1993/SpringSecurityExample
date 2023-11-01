package uz.architect.springsecurityexample.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AdminController {

    @GetMapping("/admin")
    public HttpEntity<?> adminPage(Principal principal) {
        return ResponseEntity.ok("Welcome to Admin page %s".formatted(principal.getName()));
    }
}
