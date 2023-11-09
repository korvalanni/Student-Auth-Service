package ru.urfu.SecondLabTask.configuration;

import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
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
        return (PasswordEncoder)new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers(
                        new String[] { "/login", "/registration" })).permitAll().anyRequest())
                .authenticated()).formLogin(form -> form.loginPage("/login").permitAll()).
                logout(logout -> logout.permitAll());
        return (SecurityFilterChain)http.build();
    }
}