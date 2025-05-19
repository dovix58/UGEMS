package vu.psk.ugems.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Around("@annotation(LoggedAction) || @within(LoggedAction)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        String timestamp = LocalDateTime.now().format(formatter);

        String logMessage = String.format(
                "Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted): %s\n" +
                        "Method class: %s\n" +
                        "Method name: %s",
                timestamp,
                className,
                methodName
        );

        logger.info(logMessage);

        return joinPoint.proceed();
    }
}