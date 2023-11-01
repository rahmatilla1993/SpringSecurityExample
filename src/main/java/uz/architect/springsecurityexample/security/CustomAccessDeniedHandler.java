package uz.architect.springsecurityexample.security;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import uz.architect.springsecurityexample.payload.response.AccessDeniedResponse;

import java.io.IOException;

import static uz.architect.springsecurityexample.utils.HttpConstants.APPLICATION_JSON;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException
    ) throws IOException {

        var gson = new Gson();
        var accessDeniedResponse = new AccessDeniedResponse();
        accessDeniedResponse.setMessage(accessDeniedException.getMessage());
        accessDeniedResponse.setPath(request.getRequestURL().toString());
        String json = gson.toJson(accessDeniedResponse);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON);
        response.getWriter().println(json);
    }
}
