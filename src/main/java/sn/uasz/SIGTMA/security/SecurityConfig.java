package sn.uasz.SIGTMA.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sn.uasz.SIGTMA.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    // Injection du service utilisateur par constructeur
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Désactiver la protection CSRF pour les API REST
                .authorizeHttpRequests(auth -> auth
                        // Autoriser l'accès public uniquement à l'endpoint d'inscription
                        .requestMatchers("/api/utilisateurs/inscription").permitAll()
                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )
                // Utiliser l'authentification HTTP Basic (Login/Password via Postman)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // CORRECTION : Instanciation avec le UserDetailsService en paramètre pour éviter l'erreur
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        // Configuration de l'encodeur de mot de passe (BCrypt)
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}