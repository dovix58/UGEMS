package vu.psk.ugems.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@ConditionalOnProperty(prefix = "business.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Around("@annotation(LoggedAction) || @within(LoggedAction)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        String timestamp = LocalDateTime.now().format(formatter);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal()))
                ? authentication.getName()
                : "anonymous";

        String authorities = (authentication != null && authentication.getAuthorities() != null)
                ? authentication.getAuthorities().toString()
                : "[]";

        String logMessage = String.format(
                "Time: %s | User: %s | Roles: %s | Class: %s | Method: %s",
                timestamp,
                username,
                authorities,
                className,
                methodName
        );

        logger.info(logMessage);

        return joinPoint.proceed();
    }
}