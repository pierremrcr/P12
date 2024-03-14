package com.openclassrooms.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests(authorize -> authorize
            .anyRequest().permitAll() 
        )
        .logout(logout -> logout
            .logoutUrl("/logout") 
            .logoutSuccessUrl("/") 
            .deleteCookies("JSESSIONID") 
            .invalidateHttpSession(true) 
        )
        .csrf().disable(); 
    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
