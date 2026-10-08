package org.example.proyecto1;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final Environment env;

    public HomeController(Environment env) {
        this.env = env;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "name", defaultValue = "Andy") String name) {
        
        String html = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "<title>Dashboard</title>" +
            "<style>" +
            "body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background-color: #f8f9fa; color: #333; padding: 40px; }" +
            ".container { max-width: 1000px; margin: 0 auto; }" +
            "h1 { font-size: 28px; margin-bottom: 30px; font-weight: normal; }" +
            ".grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; }" +
            ".card { background: white; border-radius: 12px; padding: 25px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }" +
            ".card-title { font-weight: bold; font-size: 16px; margin-bottom: 20px; color: #444; }" +
            ".row { display: flex; justify-content: space-between; margin-bottom: 12px; font-size: 14px; }" +
            ".label { color: #666; }" +
            ".value { font-weight: 500; text-align: right; max-width: 60%; word-wrap: break-word; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<h1>Hola, " + name + "</h1>" +
            "<div class=\"grid\">" +
            
            // Card 1
            "<div class=\"card\">" +
            "<div class=\"card-title\">1. Servidor</div>" +
            row("Puerto", env.getProperty("project.configuration.server.port")) +
            row("Framework", env.getProperty("project.configuration.server.framework")) +
            row("Contexto", env.getProperty("project.configuration.server.context-path")) +
            "</div>" +

            // Card 2
            "<div class=\"card\">" +
            "<div class=\"card-title\">2. Logging</div>" +
            row("Nivel", env.getProperty("project.configuration.logging.level")) +
            row("Salida", env.getProperty("project.configuration.logging.file")) +
            row("Formato", env.getProperty("project.configuration.logging.format")) +
            "</div>" +

            // Card 3
            "<div class=\"card\">" +
            "<div class=\"card-title\">3. Base de datos</div>" +
            row("Motor", env.getProperty("project.configuration.database.engine")) +
            row("Modo", env.getProperty("project.configuration.database.mode")) +
            row("Driver", env.getProperty("project.configuration.database.driver")) +
            "</div>" +

            // Card 4
            "<div class=\"card\">" +
            "<div class=\"card-title\">4. Seguridad</div>" +
            row("Estado", env.getProperty("project.configuration.security.status")) +
            row("Credenciales", env.getProperty("project.configuration.security.credentials")) +
            row("Autenticacion", env.getProperty("project.configuration.security.authentication")) +
            "</div>" +

            // Card 5
            "<div class=\"card\">" +
            "<div class=\"card-title\">5. Actuator</div>" +
            row("Estado", env.getProperty("project.configuration.actuator.status")) +
            row("Detalles", env.getProperty("project.configuration.actuator.details")) +
            row("Endpoint", env.getProperty("project.configuration.actuator.endpoint")) +
            "</div>" +

            "</div>" +
            "</div>" +
            "</body>" +
            "</html>";
            
        return html;
    }

    private String row(String label, String value) {
        return "<div class=\"row\"><span class=\"label\">" + label + "</span><span class=\"value\">" + (value != null ? value : "N/A") + "</span></div>";
    }
}
