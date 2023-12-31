package uz.architect.springsecurityexample.security;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.architect.springsecurityexample.payload.ApiResponse;

import java.io.IOException;

import static uz.architect.springsecurityexample.utils.HttpConstants.APPLICATION_JSON;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        var loginResponse = new ApiResponse(
                authException.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        String json = new Gson().toJson(loginResponse);
        response.setContentType(APPLICATION_JSON);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(json);
    }
}
