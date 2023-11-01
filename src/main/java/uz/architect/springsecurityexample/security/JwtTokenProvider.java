package uz.architect.springsecurityexample.security;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import uz.architect.springsecurityexample.exception.JwtAuthenticationException;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Long EXPIRATION_TIME = 36_000_000L;

    @Value("${jwt.secretKey}")
    private String SECRET;

    public String createToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts
                    .parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());

        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException exception) {
            throw new JwtAuthenticationException("Jwt token is expired or invalid");
        }
    }

    public String getUserNameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
