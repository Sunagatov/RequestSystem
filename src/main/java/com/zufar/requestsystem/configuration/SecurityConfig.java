package com.zufar.requestsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder,
                          CustomUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"requests", "users", "api/requests/*", "api/users/*").access("hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
                .antMatchers(HttpMethod.POST, "addRequest", "addUser", "api/requests/*", "api/users/*").access("hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
                .antMatchers(HttpMethod.PUT, "api/requests/*", "api/users/*").access("hasRole('ADMIN_ROLE')")
                .antMatchers(HttpMethod.DELETE, "api/requests/", "api/users/").access("hasRole('ADMIN_ROLE')")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }
}