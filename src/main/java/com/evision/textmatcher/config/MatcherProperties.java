package com.evision.textmatcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "matcher")
public class MatcherProperties {
    private String baseFilePath;
    private String poolDirectory;

    public String getBaseFilePath() {
        return baseFilePath;
    }

    public void setBaseFilePath(String baseFilePath) {
        this.baseFilePath = baseFilePath;
    }

    public String getPoolDirectory() {
        return poolDirectory;
    }

    public void setPoolDirectory(String poolDirectory) {
        this.poolDirectory = poolDirectory;
    }
}