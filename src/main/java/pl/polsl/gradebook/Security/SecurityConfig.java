package pl.polsl.gradebook.Security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
//                .requestMatchers("/**").permitAll()
                        .requestMatchers("/images/**", "/styles/**", "js/**").permitAll()
                        .requestMatchers("/choose-account").permitAll()
                        .requestMatchers("/").permitAll()
                // będzie hasAnyRole("STUDENT","TEACHER","HEADMASTER") tak gdzie trzeba
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/teacher-panel").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().permitAll()
        );
        http.formLogin(login -> login.loginPage("/login").permitAll());
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();

        // Config for logging out
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .logoutSuccessUrl("/")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}