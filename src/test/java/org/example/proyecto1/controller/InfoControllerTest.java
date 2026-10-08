package org.example.proyecto1.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.example.proyecto1.config.AppInfoProperties;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
@EnableConfigurationProperties(AppInfoProperties.class)
@ActiveProfiles("dev")
class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsVersionedApplicationMetadataWithValidDeveloperEmail() throws Exception {
        String credentials = Base64.getEncoder().encodeToString(
                "andy:andy123".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(get("/api/v1/info").header("Authorization", "Basic " + credentials))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gestor de Inventario"))
                .andExpect(jsonPath("$.version").value("0.0.1-SNAPSHOT"))
                .andExpect(jsonPath("$.description").value("API del Gestor de Inventario para desarrollo."))
                .andExpect(jsonPath("$.environment").value("dev"))
                .andExpect(jsonPath("$.developer.name").value("Andy Laglaguano"))
                .andExpect(jsonPath("$.developer.email", matchesPattern("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")))
                .andExpect(jsonPath("$.developer.role").value("Desarrollador Principal"));
    }

    @Test
    void exposesTheUnversionedCompatibilityPath() throws Exception {
        String credentials = Base64.getEncoder().encodeToString(
                "andy:andy123".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(get("/api/info").header("Authorization", "Basic " + credentials))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gestor de Inventario"));
    }
}
