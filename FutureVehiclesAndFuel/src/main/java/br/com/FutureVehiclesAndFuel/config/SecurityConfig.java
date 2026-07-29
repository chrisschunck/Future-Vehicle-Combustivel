package br.com.FutureVehiclesAndFuel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuracao inicial de seguranca. Para o escopo academico atual (sem
 * autenticacao de usuario final implementada ainda), os endpoints da API
 * e o console do H2 ficam liberados para facilitar testes em Postman/Insomnia.
 * Quando JWT for adicionado (como no projeto de agendamento medico), este
 * arquivo passa a exigir autenticacao nos endpoints protegidos.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/", "/css/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers("/veiculos/**", "/motoristas/**", "/alocacoes/**",
                                "/manutencoes/**", "/abastecimentos/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                );
        return http.build();
    }
}
