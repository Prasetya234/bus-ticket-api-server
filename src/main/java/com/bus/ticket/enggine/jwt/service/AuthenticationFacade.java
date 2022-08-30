package com.bus.ticket.enggine.jwt.service;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private UserRepository userRepository;
    @Autowired
    public AuthenticationFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthentication() {
        return userRepository.findByEmailAndBlockedIsFalse(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new NotFoundException("User id not found system"));
    }
}
