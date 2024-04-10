package cfm.onthi.sercurity;

import cfm.onthi.exceptions.CustomException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collection;
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
                Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody().getAudience() : (String) Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody().get("auth");
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

}
