package org.tolstikov.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки логирования.
 */
@ConfigurationProperties(prefix = "custom.logging")
public class LoggingProperties {
    private boolean enabled = true;
    private String level = "info";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}