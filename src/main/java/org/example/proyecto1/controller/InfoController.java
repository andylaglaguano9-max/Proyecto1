package org.example.proyecto1.controller;

import org.example.proyecto1.config.AppInfoProperties;
import org.example.proyecto1.dto.AppInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    private final AppInfoProperties appInfo;

    // Inyección por constructor
    public InfoController(AppInfoProperties appInfo) {
        this.appInfo = appInfo;
    }

    @GetMapping("/info")
    public AppInfoDto getInfo() {
        return new AppInfoDto(
                appInfo.getName(),
                appInfo.getVersion(),
                appInfo.getEnvironment(),
                appInfo.getDeveloperEmail()
        );
    }
}
