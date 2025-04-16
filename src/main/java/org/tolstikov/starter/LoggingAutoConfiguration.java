package org.tolstikov.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tolstikov.starter.aspect.ApiLoggingAspect;
import org.tolstikov.starter.config.LoggingProperties;

/**
 * Конфигурация для автоматической настройки логирования.
 */
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(prefix = "custom.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAutoConfiguration {

    /**
     * Создает бин для аспекта логирования.
     *
     * @param loggingProperties настройки логирования
     * @return экземпляр {@link ApiLoggingAspect}
     */
    @Bean
    public ApiLoggingAspect apiLoggingAspect(LoggingProperties loggingProperties) {
        return new ApiLoggingAspect(loggingProperties);
    }
}
