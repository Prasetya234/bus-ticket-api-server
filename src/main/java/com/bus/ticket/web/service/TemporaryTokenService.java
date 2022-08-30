package com.bus.ticket.web.service;

import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;

import java.util.Optional;

public interface TemporaryTokenService {
    TemporaryToken create(String token, User user);
    TemporaryToken reverseToken(String token);

    Optional<TemporaryToken> findTokenAndExpiredDate(String token);
    TemporaryToken remove(User user);
}
