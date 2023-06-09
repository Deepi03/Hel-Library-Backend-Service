package com.rest_api.fs14backend.config;

import com.rest_api.fs14backend.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
    public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        //corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("https://hel-library-web-app.netlify.app/"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Allow-Origin", " Access-Control-Allow-Credentials","Access-Control-Allow-Headers"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("GET", "/api/v1/books/*").permitAll()
                .requestMatchers("GET", "/api/v1/authors/*").permitAll()
                .requestMatchers("/swagger-ui/**","/swagger-ui.html","/","/v3/api-docs/**").permitAll()
                .requestMatchers("GET", "/api/v1/genres/*").permitAll()
                .requestMatchers("GET", "/api/v1/books/authors/*").permitAll()
                .requestMatchers("GET", "/api/v1/books/genres/*").permitAll()
                .requestMatchers("GET", "api/v1/admin/").hasRole("ADMIN")
                .requestMatchers("POST", "api/v1/admin/books").hasRole("ADMIN")
                .requestMatchers("POST", "api/v1/admin/authors").hasRole("ADMIN")
                .requestMatchers("POST", "api/v1/admin/genres").hasRole("ADMIN")
                .requestMatchers("PUT", "api/v1/admin/authors/*").hasRole("ADMIN")
                .requestMatchers("PUT", "api/v1/admin/books/*").hasRole("ADMIN")
                .requestMatchers("PUT", "api/v1/admin/genres/*").hasRole("ADMIN")
                .requestMatchers("DELETE", "api/v1/admin/genres/*").hasRole("ADMIN")
                .requestMatchers("DELETE", "api/v1/admin/authors/*").hasRole("ADMIN")
                .requestMatchers("DELETE", "api/v1/admin/books/*").hasRole("ADMIN")
                .requestMatchers("DELETE", "api/v1/admin/transactions/*").hasRole("ADMIN")
                .requestMatchers("GET", "api/v1/admin/users").hasRole("ADMIN")
                .requestMatchers("GET", "api/v1/admin/transactions").hasRole("ADMIN")
                .requestMatchers("POST", "api/v1/users/signup", "POST", "api/v1/users/signin").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter())
                //  Add JWT token filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
