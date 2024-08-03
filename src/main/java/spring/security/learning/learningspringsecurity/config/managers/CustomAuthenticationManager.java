package spring.security.learning.learningspringsecurity.config.managers;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.providers.APIKeyProvider;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

	private String key = "test";
	
	public CustomAuthenticationManager(String key) {
		super();
		this.key = key;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		APIKeyProvider apiKeyProvider = new APIKeyProvider(key);
		
		if(apiKeyProvider.supports(authentication.getClass()))
			return apiKeyProvider.authenticate(authentication);
		
		return authentication;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
