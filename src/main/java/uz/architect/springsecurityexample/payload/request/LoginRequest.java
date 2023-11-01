package uz.architect.springsecurityexample.payload.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Did not match email pattern")
    private String email;

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, message = "Not less than 6 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$",
            message = "Must include uppercase letter, lowercase letter and number")
    private String password;
}
