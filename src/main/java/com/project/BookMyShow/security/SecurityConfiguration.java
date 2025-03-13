package com.project.BookMyShow.security;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	DataSource datasource;
	

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable() // Disable CSRF protection
				.authorizeHttpRequests((requests) ->
						requests.anyRequest().permitAll())
				.sessionManagement(session ->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(withDefaults());
		return http.build();
//		return http.build();
	}
	
	@Bean
	public UserDetailsService userdetailservice() {
		UserDetails user1 = User.withUsername("User1")
								.password(passwordEncoder().encode("pass"))
								.roles("USER")
								.build();
		
		UserDetails admin = User.withUsername("Admin")
				.password(passwordEncoder().encode("pass"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin,user1);
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
//		jdbcUserDetailsManager.createUser(user1);
//		jdbcUserDetailsManager.createUser(admin);
//		
//		return jdbcUserDetailsManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	


}
