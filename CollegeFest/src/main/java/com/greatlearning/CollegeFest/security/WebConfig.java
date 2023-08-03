package com.greatlearning.CollegeFest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.greatlearning.CollegeFest.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebConfig {
	public DaoAuthenticationProvider myAuthProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUsr());
		daoAuthenticationProvider.setPasswordEncoder(myPwd());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	public UserDetailsService myUsr()
	{
		return(new UserDetailsServiceImpl());
		
	}
	
	@Bean
	public BCryptPasswordEncoder myPwd()
	{
	return new 	BCryptPasswordEncoder();
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/","/CollegeFest/new","/CollegeFest/save","/CollegeFest/view","/CollegeFest/search","/CollegeFest/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/CollegeFest/edit","/CollegeFest/delete").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/CollegeFest/studentlist").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/CollegeFest/403")
        .and()
        .cors().and().csrf().disable();
		
		http.authenticationProvider(myAuthProvider());
		
        return http.build();
    }

}
