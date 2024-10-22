package com.hospital.hospital_validation.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService employeeSecurityServiceImpl;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(customizer -> {
			customizer.requestMatchers(antMatcher("/WEB-INF/views/**"), antMatcher("/index"),
					antMatcher("/doctorDetailsSubmit"), antMatcher("/patientDetailsSubmit"), antMatcher("/handleError"),
					antMatcher(HttpMethod.POST, "/add")).permitAll();
			customizer.requestMatchers(antMatcher("/insertDoctor"), antMatcher("/deleteDoctor"),
					antMatcher("/updateDoctor"), antMatcher("/viewDoctorRequests"))
					.hasAuthority("admin");
			customizer.requestMatchers(antMatcher("/bookDoctorAppointment"), antMatcher("/image"),
					antMatcher("/viewRecords")).hasAuthority("patient");
//			customizer.requestMatchers(antMatcher("/dash")).hasAuthority("user");
			customizer.anyRequest().authenticated();

		});
		http.httpBasic(Customizer.withDefaults());
		

		http.formLogin(form -> form.loginPage("/loginSign").loginProcessingUrl("/loginP").permitAll())
				.logout(logout -> logout.permitAll());
		 http.exceptionHandling(exceptionHandling -> 
         exceptionHandling.accessDeniedPage("/accessDenied") );
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(employeeSecurityServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}