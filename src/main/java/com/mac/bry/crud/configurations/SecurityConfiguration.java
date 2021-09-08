package com.mac.bry.crud.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/loginform")
        .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//in memory user
		//auth.inMemoryAuthentication().withUser("mac").password("{noop}bry").roles("USER");
		
		auth.jdbcAuthentication().dataSource(dataSource).withUser("mac1").password("{noop}bry1").roles("USER");
	}
	
	

}
