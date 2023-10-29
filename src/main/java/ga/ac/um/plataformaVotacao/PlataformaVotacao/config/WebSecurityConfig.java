package ga.ac.um.plataformaVotacao.PlataformaVotacao.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final String[] ESTUDANTE_LIST_POST = {"/registar/estudante"};
    private final String[] WHITE_LIST_GET = {"/login/email={emailEstudante}&senha={senhaEstudante}", "/login/emailAdmin={emailEntity}&senha={senhaEntity}"};
    private final String[] ADMIN_LIST_POST = {"/registarAdmin"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(HttpMethod.GET, WHITE_LIST_GET).permitAll()
                            .requestMatchers(HttpMethod.POST, ESTUDANTE_LIST_POST).permitAll()
                            .requestMatchers(HttpMethod.POST, ADMIN_LIST_POST).hasRole("ADMIN")
                            .anyRequest().permitAll();
                }).formLogin(Customizer.withDefaults()).build();
    }
}