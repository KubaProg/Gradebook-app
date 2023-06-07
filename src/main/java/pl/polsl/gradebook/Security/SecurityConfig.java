package pl.polsl.gradebook.Security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
//                .requestMatchers("/**").permitAll()
                        .requestMatchers("/images/**", "/styles/**", "js/**").permitAll()
                        .requestMatchers("/landingPage").hasAnyRole("ADMIN","USER")
//                .requestMatchers("/jobList").hasAnyRole("USER","ADMIN")
//                .requestMatchers("/adminsPanel/**").hasRole("ADMIN")
//                .requestMatchers("/api/**").permitAll()
//                .requestMatchers("/helloPage").permitAll()
//                .requestMatchers("/images/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
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