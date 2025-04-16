package org.tolstikov.starter.annotation;

import java.lang.annotation.*;

/**
 * Аннотация для логирования вызовов API.
 * Используется для обозначения методов, которые требуют логирования.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogApi {
}
