package com.smart_expense_tracker.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY =
            "QXNkZmdoamtsMDEyMzQ1Njc4OUFCQ0RFRkdISUpLTE1OT1A=";
	
	private static final long EXPIRATION_TIME=1000*60*60;
	
	//private static final Key key=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private final SecretKey key = Keys.hmacShaKeyFor(
            SECRET_KEY.getBytes()
    );
	
	public String generateToken(String email) {
		return Jwts.builder().setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(key).compact();
	}
    public String extractEmail(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(key)
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }

}
