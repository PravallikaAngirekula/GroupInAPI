package com.vns.groupin.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.UserRegistration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secret = "vnssolutions";

	public String extractMblNum(String token) {
		return extractClaim(token, Claims::getSubject);
	}


	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String mobileNumber, String mblNumOtp) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, mobileNumber, mblNumOtp);
	}

	public String generateTokenByUserName(String mobileNumber) {
		Map<String, Object> claims = new HashMap<>();
		return createNewToken(claims, mobileNumber);
	}
	private String createToken(Map<String, Object> claims, String subject, String mblNumOtp) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	private String createNewToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, UserRegistration userReg) {
		final String mblNum = extractMblNum(token);
		return (mblNum.equals(userReg.getMobileNumber()) && !isTokenExpired(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String mblNum = extractMblNum(token);
		return (mblNum.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String getMobileNumberFromJwtToken(String token) {
		String mbl = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
		System.out.println(mbl);
		return mbl;
	}


	public String generateToken1(String mobileNumber, String mblNumOtp) {
		Map<String, Object> claims = new HashMap<>();
		return createToken1(claims, mobileNumber, mblNumOtp);
	}


	private String createToken1(Map<String, Object> claims, String mobileNumber, String mblNumOtp) {
		return Jwts.builder().setClaims(claims).setSubject(mobileNumber).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	
}
