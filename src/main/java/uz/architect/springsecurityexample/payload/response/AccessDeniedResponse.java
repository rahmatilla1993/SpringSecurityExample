package uz.architect.springsecurityexample.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccessDeniedResponse {
    private String message;
    private final String date = LocalDateTime.now().toString();
    private String path;
}
