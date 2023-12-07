package ru.urfu.SecondLabTask.configuration;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/registration", "/user/**", "/password", "/login", "/project").permitAll()
                .anyRequest().authenticated())
                .formLogin((login) -> login
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/user", true)
                        .failureUrl("/login?error=true"))
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}