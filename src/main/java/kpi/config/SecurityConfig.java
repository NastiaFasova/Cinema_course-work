package kpi.config;

import kpi.security.JwtCsrfFilter;
import kpi.security.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                    .addFilterAt(new JwtCsrfFilter(jwtTokenRepository, resolver), CsrfFilter.class)
                    .csrf().ignoringAntMatchers("/**")
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/movies/**",
                        "/movie-sessions/**", "/cinema-halls/**")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/orders/complete",
                        "/shopping-carts/**")
                    .hasRole("USER")
                    .antMatchers(HttpMethod.GET,  "/hello", "/movies",
                        "/movie-sessions", "/cinema-halls", "/orders")
                    .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/login")
                    .authenticated()
                .and()
                    .httpBasic()
                    .authenticationEntryPoint(((request, response, e) -> resolver.resolveException(request, response, null, e)));
        ;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register");
    }
}
