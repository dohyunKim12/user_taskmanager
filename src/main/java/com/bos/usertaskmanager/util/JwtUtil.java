package com.bos.usertaskmanager.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JwtUtil is a utility class for creating and validating JSON Web Tokens (JWT).
 * It provides methods to create a JWT token, validate it, and extract authentication information from it.
 * The class uses the io.jsonwebtoken library for JWT operations.
 * <p>
 * The secret key used for signing the tokens should be kept secure and not hard-coded in production code.
 * The validity period of the token is set to 1 hour (3600000 milliseconds).
 *
 * @author dohyun.kim
 */

@Component
public class JwtUtil {

    private final String secretKey = "mySecretKeyMySecretKeyMySecretKey"; // Should be at least 256 bits
    private final long validityInMilliseconds = 3600000L; // 1Hour

    // Create JWT Token
    public String createToken(Authentication auth) {
        Claims claims = Jwts.claims().setSubject(auth.getName());
        claims.put("roles", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Get Authentication from JWT Token
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                ((List<?>) claims.get("roles")).stream()
                        .map(role -> new SimpleGrantedAuthority((String) role))
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
