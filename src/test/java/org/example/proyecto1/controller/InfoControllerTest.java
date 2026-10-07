package org.example.proyecto1.controller;

import org.example.proyecto1.config.AppInfoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfoController.class)
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppInfoProperties appInfoProperties;

    @Test
    public void testGetInfo_ReturnsHttp200AndValidJson() throws Exception {
        // Configuramos el Mock
        when(appInfoProperties.getName()).thenReturn("Gestor de Inventario");
        when(appInfoProperties.getVersion()).thenReturn("0.0.1-SNAPSHOT");
        when(appInfoProperties.getEnvironment()).thenReturn("Development");
        when(appInfoProperties.getDeveloperEmail()).thenReturn("andy.laglaguano.dev@empresa.com");

        // Validamos HTTP 200 + estructura JSON con al menos 3 campos + email válido
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gestor de Inventario"))
                .andExpect(jsonPath("$.version").value("0.0.1-SNAPSHOT"))
                .andExpect(jsonPath("$.environment").value("Development"))
                .andExpect(jsonPath("$.developerEmail").value("andy.laglaguano.dev@empresa.com"));
    }

    @Test
    public void testGetInfo_DeveloperEmailHasValidFormat() throws Exception {
        // Verificamos que el email tiene formato válido (contiene @)
        when(appInfoProperties.getName()).thenReturn("Gestor de Inventario");
        when(appInfoProperties.getVersion()).thenReturn("0.0.1-SNAPSHOT");
        when(appInfoProperties.getEnvironment()).thenReturn("Development");
        when(appInfoProperties.getDeveloperEmail()).thenReturn("test@valido.com");

        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.developerEmail").value(org.hamcrest.Matchers.containsString("@")));
    }
}
