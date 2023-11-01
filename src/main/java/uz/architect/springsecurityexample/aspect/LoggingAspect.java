package uz.architect.springsecurityexample.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uz.architect.springsecurityexample.annotation.Logger;
import uz.architect.springsecurityexample.security.UserSecurity;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("within(uz.architect.springsecurityexample.service.ProductService)")
    private static void productServicePointCut() {
    }

    @Pointcut("@annotation(uz.architect.springsecurityexample.annotation.Logger)")
    private static void logger() {
    }

    @After("logger()")
    public static void logAfter(JoinPoint joinPoint) {
        String username = getUsername();
        String message = "";
        String tableName = "";
        var signature = (MethodSignature) joinPoint.getSignature();

        HttpServletRequest request = getRequest();
        String httpMethod = request.getMethod();
        String endPoint = request.getRequestURL().toString();

        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Logger.class)) {
            Logger logger = method.getAnnotation(Logger.class);
            message = logger.message();
            tableName = logger.tableName();
        }

        log.info("{}, {}, {}, {}, {}, {}", username, signature, endPoint,
                httpMethod,
                message,
                tableName
        );
    }

    private static String getUsername() {
        return ((UserSecurity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder
                        .getRequestAttributes()))
                .getRequest();
    }
}
