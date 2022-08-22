package com.bus.ticket.enggine.jwt.service;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacade {
    private static UserRepository userRepository;

    @Autowired
    public AuthenticationFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static User getAuthentication() {
        return userRepository.findByFirstNameAndBlockedIsFalse(String.valueOf(SecurityContextHolder.getContext().getAuthentication())).orElseThrow(() -> new NotFoundException("User id not found system"));
    }
}
