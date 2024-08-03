package spring.security.learning.learningspringsecurity.config.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.authentication.APIKeyAuthentication;

@AllArgsConstructor
public class APIKeyProvider implements AuthenticationProvider{

	private String key = "test";
	public APIKeyProvider( String key) {
		this.key = key;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		APIKeyAuthentication apiKeyAuthentication =  (APIKeyAuthentication) authentication;
		if(key.equalsIgnoreCase(apiKeyAuthentication.getKey())) {
			apiKeyAuthentication.setAuthenticated(true);
			return apiKeyAuthentication;
		}else
			throw new BadCredentialsException("bad");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return APIKeyAuthentication.class.equals(authentication);
	}

}
