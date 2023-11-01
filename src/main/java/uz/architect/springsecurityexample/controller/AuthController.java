package uz.architect.springsecurityexample.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.architect.springsecurityexample.payload.request.LoginRequest;
import uz.architect.springsecurityexample.service.AuthUserService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserService authUserService;

    @Autowired
    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginRequest loginRequest,
                               BindingResult bindingResult) {
        var validationResult = authUserService.getValidationResult(bindingResult);
        if (!ObjectUtils.isEmpty(validationResult)) {
            return ResponseEntity.badRequest().body(validationResult);
        }

        var response = authUserService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
