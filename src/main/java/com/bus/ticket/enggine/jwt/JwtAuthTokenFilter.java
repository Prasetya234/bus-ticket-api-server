package com.bus.ticket.enggine.jwt;

import com.bus.ticket.enggine.jwt.service.UserDetailsServiceImpl;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.repository.TemporaryTokenRepository;
import com.bus.ticket.web.service.TemporaryTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TemporaryTokenService temporaryTokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwt(request);
            if (jwt != null) {
                Optional<TemporaryToken> token = temporaryTokenService.findTokenAndExpiredDate(jwt);
                if (token.isPresent()) {
                    String username = refreshToken(token.get().getToken()).getUserId().getFirstName();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }
        filterChain.doFilter(request, response);
    }
    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

    private TemporaryToken refreshToken(String token) {
        return temporaryTokenService.reverseToken(token);
    }
}
