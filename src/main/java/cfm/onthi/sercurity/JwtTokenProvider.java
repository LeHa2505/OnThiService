package cfm.onthi.sercurity;

import cfm.onthi.exceptions.CustomException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.public-key}")
    private String publicKey;
    @Value("${jwt.private-key}")
    private String privateKey;

    public String getAuthience(String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = factory.generatePublic(spec);
        String authience = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody().getAudience() != null ?
                Jwts.parser()
                        .setSigningKey(pubKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getAudience() : (String) Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody().get("sub");
        return authience;
    }

    public String resolveToken(jakarta.servlet.http.HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public Collection<? extends CustomGrantedAuthority> getRoles(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = factory.generatePublic(spec);

        Jws<Claims> jwt = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);

        List<String> roleOfUser = (List<String>) jwt.getBody().get("roles");
        List<String> permission = (List<String>) jwt.getBody().get("permission");
        List<CustomGrantedAuthority> roles = roleOfUser.stream()
                .map(c -> new CustomGrantedAuthority(c, permission != null ? permission : null))
                .collect(Collectors.toList());
        return roles;
    }

    public boolean isInvalid(String token) {
        try {
            //Lấy public key RSA 256
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);

            return true;
        } catch (JwtException | NoSuchAlgorithmException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            throw new CustomException("JWT claims string is empty: {}", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String userName, String userType,String typeToken) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("roles", userType);

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        if(typeToken.equals("REFRESH_TOKEN"))
            expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION * 7);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);

        PKCS8EncodedKeySpec specPK = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory factoryPK = KeyFactory.getInstance("RSA");
        PrivateKey priKey = factoryPK.generatePrivate(specPK);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(currentDate)//
                .setHeaderParam("typ", "JWT")
                .setExpiration(expireDate)//
                .signWith(SignatureAlgorithm.RS256, priKey)//
                .compact();
    }
/*    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }*/
}
