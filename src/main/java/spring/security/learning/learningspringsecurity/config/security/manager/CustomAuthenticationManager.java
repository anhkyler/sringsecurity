package spring.security.learning.learningspringsecurity.config.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import spring.security.learning.learningspringsecurity.config.security.provider.CustomAuthenticationProvider;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider ;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		customAuthenticationProvider = new CustomAuthenticationProvider();
		if(customAuthenticationProvider.supports(authentication.getClass()))
			return customAuthenticationProvider.authenticate(authentication);
		throw new BadCredentialsException("not the same provider");
	}

}