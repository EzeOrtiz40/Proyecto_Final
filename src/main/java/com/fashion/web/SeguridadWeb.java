package com.fashion.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public  class SeguridadWeb{
   @Bean
   public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.authorizeRequests(requests -> requests.antMatchers("/css/*", "/js/*", "/img/*", "/**")
               .permitAll());
        return http.build();
   }
   
}
