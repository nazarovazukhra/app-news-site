package uz.pdp.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "MaxfiySuzHechKimBilmasin";

    public String generateToken(String username, Lavozim lavozims) {

        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", lavozims.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;

    }


    public String getUsernameFromToken(String token) {

        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        } catch (Exception e) {
            return null;
        }
    }
}
