package br.com.lca.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class JWTAuthFilterConfig {

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-config",
            "/swagger-resources/**",
            "/api/docs/**"
    };

    private static final String[] AUTH_BLUE_LIST = {
            "/users/**",
            "/products/**",
            "/orders/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authReq -> authReq.requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers(AUTH_WHITE_LIST)
                        .permitAll()
                        .requestMatchers("/products", "/orders")
                        .hasAuthority("ADMIN")
                        .requestMatchers(AUTH_BLUE_LIST)
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080", "https://luzcameraacao-api.up.railway.app"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT"));
        configuration.setAllowedHeaders(List.of(JWTAuthFilter.AUTH_HEADER, "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
