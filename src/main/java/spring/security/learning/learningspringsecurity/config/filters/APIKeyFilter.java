package spring.security.learning.learningspringsecurity.config.filters;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.authentication.APIKeyAuthentication;
import spring.security.learning.learningspringsecurity.config.managers.CustomAuthenticationManager;

@AllArgsConstructor
public class APIKeyFilter extends OncePerRequestFilter {

	private  String key = "test";
	public APIKeyFilter(String key) {
		this.key = key;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(key);
		APIKeyAuthentication apiKeyAuthentication = new APIKeyAuthentication();
		String requestHeaderKey = request.getHeader("x-api-key");
		if(requestHeaderKey == null || requestHeaderKey.equalsIgnoreCase(null))
			filterChain.doFilter(request, response);
		apiKeyAuthentication.setKey(requestHeaderKey);
		
		try {
			Authentication resultCustom = customAuthenticationManager.authenticate(apiKeyAuthentication);
			if(resultCustom.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(resultCustom);
				filterChain.doFilter(request, response);
			}else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}catch(Exception aEx) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
		
		
	}

}
