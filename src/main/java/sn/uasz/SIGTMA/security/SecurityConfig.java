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
    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
            CustomAuthenticationSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Optionnel: activer pour plus de sécurité en prod
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        .requestMatchers("/login", "/register", "/api/utilisateurs/inscription").permitAll()
                        .requestMatchers("/admin/**", "/api/utilisateurs/**").hasRole("ADMINISTRATEUR")
                        .requestMatchers("/api/utilisateurs/profil").authenticated()
                        .requestMatchers("/bibliothecaire/**").hasRole("BIBLIOTHECAIRE")
                        .requestMatchers("/api/filieres/**").hasAnyRole("ADMINISTRATEUR", "AIDE_BIBLIOTHECAIRE")
                        .requestMatchers("/aide-bibliothecaire/**", "/api/aide/**", "/api/theses/depot",
                                "/api/etudiants/**", "/api/encadrants/**")
                        .hasRole("AIDE_BIBLIOTHECAIRE")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // CORRECTION : Instanciation avec le UserDetailsService en paramètre pour
        // éviter l'erreur
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