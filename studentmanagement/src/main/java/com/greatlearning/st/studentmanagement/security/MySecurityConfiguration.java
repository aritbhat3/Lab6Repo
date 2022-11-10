package com.greatlearning.st.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.st.studentmanagement.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(getUserDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/student/list","/student/showFormForAdd","/student/save","/student/search","/student/403").hasAnyAuthority("ADMIN","USER")
		.antMatchers("/student/showFormForUpdate","/student/delete").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/student/403")
		.and()
		.cors().and().csrf().disable();
	}
}
