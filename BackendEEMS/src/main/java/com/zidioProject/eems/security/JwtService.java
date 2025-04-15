package com.zidioProject.eems.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zidioProject.eems.Entity.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;
	
	public String getSecret() {
		return secret;
	}
	
	
	public String generateToken(Employee emp) {
		
		Map<String, Object> claim = new HashMap<>();
		
		return Jwts
				.builder()
				.claims()
				.add(claim)
				.subject(emp.getEmail())
				.issuer("EEMS")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60*10*1000))
				.and()
				.signWith(generateKey())
				.compact();				
	}


	private SecretKey generateKey() {	
		byte[] decode = Decoders.BASE64.decode(getSecret());	
		return Keys.hmacShaKeyFor(decode);
	}
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractEmployeeId(String token) {
        Claims claims = extractAllClaims(token);
        return Long.parseLong(claims.get("employeeId").toString());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean validateToken(String token, String userName) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userName) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
	
	
	
}
