package com.fashion.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SeguridadWeb extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
    .authorizeRequests(requests -> requests
        .antMatchers("/", "/css/**", "/js/**", "/img/**", "/registrar").permitAll()
        .anyRequest().permitAll())
    .formLogin(login -> login
        .loginPage("/login")
        .permitAll())
    .logout(logout -> logout
        .permitAll());

    }
}
