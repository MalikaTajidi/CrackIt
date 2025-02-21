package com.crackit.crackit.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Security configuration class that sets up JWT-based authentication and authorization.
 * defines public endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = { "/register", "/login", "/test/**" };

    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Constructor for dependency injection of JwtRequestFilter.
     *
     * @param jwtRequestFilter custom filter to handle JWT token validation.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object to configure HTTP security rules.
     * @return the configured SecurityFilterChain.
     * @throws Exception if there is an error during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF is disabled for APIs using JWT
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll() // Allows public access to specified endpoints
                        .anyRequest().authenticated() // Requires authentication for all other endpoints
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Adds JWT filter before default authentication

        return http.build();
    }

    /**
     * Configures password encoding using BCrypt.
     *
     * @return a PasswordEncoder with BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the AuthenticationManager bean.
     *
     * @param configuration the authentication configuration provided by Spring.
     * @return the AuthenticationManager for handling authentication.
     * @throws Exception if an error occurs during manager retrieval.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}

