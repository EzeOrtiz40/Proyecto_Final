package com.fashion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

import com.fashion.web.servicios.UsuarioServicio;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public  class SeguridadWeb{

   @Autowired
   public UsuarioServicio usuarioServicio;

   @Autowired
   public void cofigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
      auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());

   }

   @Bean
   public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.authorizeRequests(requests -> requests
               .antMatchers("/admin/*").hasRole("ADMIN")
               .antMatchers("/css/*", "/js/*", "/img/*", "/**")
               .permitAll())
               .formLogin(login -> login
                       .loginPage("/login")
                       .loginProcessingUrl("/logincheck")
                       .usernameParameter("email")
                       .passwordParameter("password")
                       .defaultSuccessUrl("/inicio")
                       .permitAll())
               .logout(logout -> logout
               .logoutUrl("/logout")
               .logoutSuccessUrl("/")
               .permitAll()).csrf(csrf -> csrf.disable());

         return http.build();
   }
}
