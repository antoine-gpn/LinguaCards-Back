package app.linguacards.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive la protection CSRF pour les API REST
                .csrf(csrf -> csrf.disable())
                // Configurer la session comme "stateless" pour les API REST
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Désactiver le formulaire de login par défaut
                .formLogin(form -> form.disable())
                // Désactiver l'authentification HTTP Basic
                .httpBasic(httpBasic -> httpBasic.disable())
                // Définir les autorisations des requêtes HTTP - Tout est accessible temporairement
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()  // TEMPORAIRE : autorise toutes les requêtes sans authentification
                );

        return http.build();
    }
}
