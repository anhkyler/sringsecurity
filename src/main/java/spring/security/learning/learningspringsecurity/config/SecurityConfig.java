package spring.security.learning.learningspringsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//		return httpSecurity.httpBasic().and().authorizeHttpRequests().anyRequest().permitAll().and().build();
		return httpSecurity.httpBasic()
				.and()
//				.authorizeRequests()
//				.securityMatcher("/demo")httpSecurity.autho
//				.anyRequest()
//				.hasAnyRole("ADMIN")
				.authorizeHttpRequests(auth -> 
						auth.requestMatchers("/demo").hasAnyAuthority("read").anyRequest().authenticated())
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
		User aUser = (User) User.withUsername("bill").password(passwordEncoder().encode("123456")).roles("ADMIN").authorities("read").build();
		uds.createUser(aUser);
		return uds;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
