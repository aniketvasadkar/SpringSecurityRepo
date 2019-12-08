package com.sec.jwt.demo.springsecurityjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET = "secret";

 public boolean validateToken(String token, UserDetails userDetails){
     final String username = getUsernameFromToken(token);
     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
 }

 public String getUsernameFromToken(String token){
     return getClaimFromToken(token, Claims :: getSubject);
 }

 public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
 }

 public Boolean isTokenExpired(String token){
     Date expirationDate = getExpirationDateFromToken(token);
     return expirationDate.before(new Date());
 }

    public String createToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (10 * 60 * 1000 * 60)))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    public  Claims getAllClaims(String token){
        return Jwts
                    .parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsFunction){
        final Claims allClaims = getAllClaims(token);
        return claimsFunction.apply(allClaims);

    }
}
