package hh.sof03.kalenteriprojekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// import static method
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration {
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                                .requestMatchers(toH2Console()).permitAll()
                                                .anyRequest().authenticated())
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers(toH2Console()))
                                .headers(headers -> headers
                                                .frameOptions(frameoptions -> frameoptions
                                                                .disable()))
                                .formLogin(formlogin -> formlogin
                                                .defaultSuccessUrl("/index", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll());
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

        // lisätty päivämääriä käsittelevä engine
        @Bean
        public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
                SpringTemplateEngine engine = new SpringTemplateEngine();
                engine.addDialect(new Java8TimeDialect());
                engine.setTemplateResolver(templateResolver);
                return engine;
        }
}