package com.bus.ticket.enggine.jwt.service;

import com.bus.ticket.enggine.jwt.data.UserPrinciple;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndBlockedIsFalse(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email Or Password Not Found")
                );
        return UserPrinciple.build(user);
    }
}
