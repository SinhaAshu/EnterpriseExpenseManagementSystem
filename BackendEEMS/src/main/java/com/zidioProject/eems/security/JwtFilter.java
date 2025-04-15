package com.zidioProject.eems.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String extractedtoken = authHeader.substring(7);
		String userName = jwtService.extractUsername(extractedtoken);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (userName != null && authentication == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtService.validateToken(extractedtoken, userName)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}
		
		filterChain.doFilter(request, response);
		
	}

}
