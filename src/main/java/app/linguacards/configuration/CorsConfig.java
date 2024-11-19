package app.linguacards.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Autorise toutes les routes de votre API
                .allowedOrigins("http://localhost:4200")  // Autorise les requêtes depuis localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Méthodes HTTP autorisées
                .allowedHeaders("*")  // Autorise tous les en-têtes
                .allowCredentials(true);  // Permet d'envoyer des cookies et autres informations
    }
}
