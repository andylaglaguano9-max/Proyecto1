package org.example.proyecto1.controller;

import org.example.proyecto1.config.AppInfoProperties;
import org.example.proyecto1.dto.AppInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final AppInfoProperties appInfo;

    public InfoController(AppInfoProperties appInfo) {
        this.appInfo = appInfo;
    }

    @GetMapping({"/api/info", "/api/v1/info"})
    public AppInfoDto info() {
        return AppInfoDto.from(appInfo);
    }
}
