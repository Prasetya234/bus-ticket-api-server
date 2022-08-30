package com.bus.ticket.enggine.jwt;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.TemporaryTokenRepository;
import com.bus.ticket.web.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider  {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private TemporaryTokenRepository temporaryTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public JwtProvider(TemporaryTokenRepository temporaryTokenRepository, UserRepository userRepository) {
        this.temporaryTokenRepository = temporaryTokenRepository;
        this.userRepository = userRepository;
    }
    @Value("bootcamp_")
    private String jwtSecret;
    @Value("1800000")  // 30 minute
    private int jwtExpiration;

    public String generateJwtToken(UserDetails userDetails) {
        String jwt = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        User user = userRepository.findByEmailAndBlockedIsFalse(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("userNot found"));
        Optional<TemporaryToken> valid = temporaryTokenRepository.findByUserId(user);
        if (valid.isPresent()) temporaryTokenRepository.deleteById(valid.get().getId());
        return jwt;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}
