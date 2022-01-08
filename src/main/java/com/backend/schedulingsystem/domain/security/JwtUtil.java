package com.backend.schedulingsystem.domain.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class JwtUtil {
    private final String ROLES_KEY = "role";

    private JwtParser parser;

    private String secretKey;
    private long validityInMilliseconds;

//    @Autowired
    public JwtUtil() {
        this.secretKey= "secret-key-for-encryption";
        this.validityInMilliseconds = 600000;
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Create JWT string given username and roles.
     *
     * @param username
     * @return jwt string
     */
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLES_KEY, role);
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Validate the JWT String
     *
     * @param token JWT string
     * @return true if valid, false otherwise
     */
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Get the username from the token string
     *
     * @param token jwt
     * @return username
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Get the roles from the token string
     *
     * @param token jwt
     * @return username
     */
//    public List<GrantedAuthority> getRoles(String token) {
//        List<Map<String, String>>  roleClaims = Jwts.parser().setSigningKey(secretKey)
//                .parseClaimsJws(token).getBody().get(ROLES_KEY, List.class);
//        return roleClaims.stream().map(roleClaim ->
//                new SimpleGrantedAuthority(roleClaim.get("authority")))
//                .collect(Collectors.toList());
//    }
    public GrantedAuthority getRoles(String token) {
        String roleClaims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get(ROLES_KEY, String.class);
        return new SimpleGrantedAuthority(roleClaims);
    }
}
