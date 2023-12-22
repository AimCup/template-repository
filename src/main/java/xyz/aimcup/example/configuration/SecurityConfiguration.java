package xyz.aimcup.example.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("!test")
@Slf4j
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .oauth2Client(Customizer.withDefaults())
            .oauth2Login(Customizer.withDefaults())
            .sessionManagement(Customizer.withDefaults())
            .authorizeHttpRequests(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest()
                .fullyAuthenticated())
            .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
            .build();
    }
}
