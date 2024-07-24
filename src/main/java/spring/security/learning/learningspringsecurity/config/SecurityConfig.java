package spring.security.learning.learningspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.security.filter.CustomAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig  {

	@Autowired
	CustomAuthenticationFilter customAuthenticationFilter;
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(requests -> requests.anyRequest().authenticated())
                .build();
	}
}
