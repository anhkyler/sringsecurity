package spring.security.learning.learningspringsecurity.config.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import spring.security.learning.learningspringsecurity.config.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Value("${key.secret}")
	private String key;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthentication customAuthentication = (CustomAuthentication)authentication;
		String headKey = customAuthentication.getKey();
		if("ABCDEF".equalsIgnoreCase(headKey)) {
			CustomAuthentication cusResult = new CustomAuthentication(true, headKey);
			return cusResult;
			
		}
		throw new BadCredentialsException("not the same key");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return CustomAuthentication.class.equals(authentication);
	}

}
