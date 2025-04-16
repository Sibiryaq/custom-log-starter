package org.tolstikov.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.tolstikov.starter.annotation.LogApi;
import org.tolstikov.starter.config.LoggingProperties;

import java.util.Arrays;

/**
 * Аспект для логирования вызовов методов, аннотированных {@link LogApi}.
 */
@Aspect
@Component
@Slf4j
public class ApiLoggingAspect {

    private final LoggingProperties props;

    public ApiLoggingAspect(LoggingProperties props) {
        this.props = props;
    }

    /**
     * Обработчик для методов, аннотированных {@link LogApi}.
     * Логирует вызовы методов, их аргументы, время выполнения и результат.
     *
     * @param joinPoint точка соединения
     * @param logApi    аннотация логирования
     * @return результат выполнения метода
     * @throws Throwable если возникает исключение
     */
    @Around("@annotation(logApi)")
    public Object logExecution(ProceedingJoinPoint joinPoint, LogApi logApi) throws Throwable {
        if (!props.isEnabled()) {
            return joinPoint.proceed();
        }

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logMessage("Вызов метода: {} с аргументами: {}", methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        logMessage("Метод {} завершён за {} мс. Результат: {}", methodName, duration, result);

        return result;
    }

    /**
     * Логирует сообщение с указанным уровнем логирования.
     *
     * @param message сообщение для логирования
     * @param args    аргументы для сообщения
     */
    private void logMessage(String message, Object... args) {
        switch (props.getLevel().toLowerCase()) {
            case "debug" -> log.debug(message, args);
            case "warn" -> log.warn(message, args);
            case "error" -> log.error(message, args);
            default -> log.info(message, args);
        }
    }
}