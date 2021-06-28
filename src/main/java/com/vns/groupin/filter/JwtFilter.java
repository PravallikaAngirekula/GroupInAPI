package com.vns.groupin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.service.CustomUserRegService;
import com.vns.groupin.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserRegService custUserRegService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		String token = null;
		String mobileNumber = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			mobileNumber = jwtUtil.extractMblNum(token);
		}

		if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails usrdetails = custUserRegService.loadUserByUsername(mobileNumber);
			if (jwtUtil.validateToken(token, usrdetails)) {
				UsernamePasswordAuthenticationToken userpswdAuthToken = new UsernamePasswordAuthenticationToken(
						usrdetails, null, usrdetails.getAuthorities());
				userpswdAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userpswdAuthToken);
			}
//			else {
////				UserRegistration user = null;
////			custUserRegService.saveUserDetails(user);
////				
//				}

			}
			
			filterChain.doFilter(request, response);
			
//			UserRegistration reg = (UserRegistration) custUserRegService.loadUserByUsername(mobileNumber);
//			if (jwtUtil.validateToken(token, reg)) {
//				MblNumAndOtpAuthenticationToken mbltoken = new MblNumAndOtpAuthenticationToken();
//
//			}
		}

	

}
