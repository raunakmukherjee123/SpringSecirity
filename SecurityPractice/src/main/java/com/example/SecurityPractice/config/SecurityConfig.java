package com.example.SecurityPractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
//        UserDetails admin= User.withUsername("Admin1")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user= User.withUsername("User1")
//                .password(encoder.encode("1234"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);

        return new UserInfoUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
//        http.csrf(csrf->csrf.disable());
//
//       http.authorizeHttpRequests((request)->request
//               .requestMatchers("/api/product/welcome").permitAll()
//               .anyRequest().authenticated());
//
//       http.formLogin();
//
//        return http.build();

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/product/welcome","/api/product/new/user").permitAll()
                .anyRequest().authenticated());

        http.formLogin(form -> form
                .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService) {

        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
}
