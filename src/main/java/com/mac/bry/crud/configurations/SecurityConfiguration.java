package com.mac.bry.crud.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/loginform").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/registerform").permitAll()
		.antMatchers("/resetPasswordForm").permitAll()
		.antMatchers("/resetPassword").permitAll()
		.anyRequest().authenticated()
				.and().formLogin().loginPage("/loginform");
		        
	}

}
