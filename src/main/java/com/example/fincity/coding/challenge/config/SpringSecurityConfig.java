package com.example.fincity.coding.challenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  // create two users for demo
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication().withUser("demouser@demo.com").password("{noop}passworD")
        .roles("USER").and().withUser("demoadmin@demo.com").password("{noop}passworD")
        .roles("USER", "ADMIN");
  }

  // secure the endpoints with HTTP basic authentication

  protected void configure(HttpSecurity http) throws Exception {

    http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/cars/**")
        .hasRole("USER").antMatchers(HttpMethod.POST, "/cars").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/cars/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/cars/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/cars/**").hasRole("ADMIN").and().csrf().disable();;
  }
}
