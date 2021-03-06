package com.sathyatech.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login","/register","/saveUser").permitAll()
		.antMatchers("/uom**").hasAuthority("ROLE_USER")
		.antMatchers("/admin**").hasAuthority("ROLE_ADMIN")
		//.antMatchers("/uom**").access("hasRole('ROLE_USER')")
		//.antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")//hasAuthority("[ROLE_ADMIN]")
		
	
		.anyRequest().authenticated()
		
		.and().csrf().disable()
		
		.formLogin()
		.loginPage("/login").failureUrl("/login?error=true")
		//.loginProcessingUrl("/activeChk")
		.defaultSuccessUrl("/activeChk")
		.usernameParameter("username")
		.passwordParameter("password")
		
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout=true")
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/AccessDenied");
	}
}
