package spring.security.learning.learningspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.filters.APIKeyFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

	private String key = "test";

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
        		.and().addFilterBefore(new APIKeyFilter(key), BasicAuthenticationFilter.class)
        		.authorizeRequests().anyRequest().authenticated().and()
        		.build();
    }
	
	
	
}
