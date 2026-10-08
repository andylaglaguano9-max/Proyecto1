package org.example.proyecto1.dto;

import org.example.proyecto1.config.AppInfoProperties;

public record AppInfoDto(
        String name,
        String version,
        String description,
        String environment,
        DeveloperInfo developer) {

    public static AppInfoDto from(AppInfoProperties properties) {
        AppInfoProperties.Developer developer = properties.getDeveloper();
        return new AppInfoDto(
                properties.getName(),
                properties.getVersion(),
                properties.getDescription(),
                properties.getEnvironment(),
                new DeveloperInfo(developer.getName(), developer.getEmail(), developer.getRole()));
    }

    public record DeveloperInfo(String name, String email, String role) {
    }
}
