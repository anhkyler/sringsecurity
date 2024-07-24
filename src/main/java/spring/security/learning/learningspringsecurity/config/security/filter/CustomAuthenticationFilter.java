package spring.security.learning.learningspringsecurity.config.security.filter;


import java.io.IOError;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import spring.security.learning.learningspringsecurity.config.security.authentication.CustomAuthentication;
import spring.security.learning.learningspringsecurity.config.security.manager.CustomAuthenticationManager;


@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter{

	private final CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager();
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key = request.getHeader("key");
		CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
		Authentication a = customAuthenticationManager.authenticate(customAuthentication);
		if(a.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(a);
			filterChain.doFilter(request, response);
		}
		
		
	}

}
