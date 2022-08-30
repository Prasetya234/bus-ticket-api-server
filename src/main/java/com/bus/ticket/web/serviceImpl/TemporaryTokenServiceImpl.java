package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.utils.Utils;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.TemporaryTokenRepository;
import com.bus.ticket.web.service.TemporaryTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TemporaryTokenServiceImpl extends Utils implements TemporaryTokenService  {

    TemporaryTokenRepository temporaryTokenRepository;

    @Autowired
    public TemporaryTokenServiceImpl(TemporaryTokenRepository temporaryTokenRepository) {
        this.temporaryTokenRepository = temporaryTokenRepository;
    }

    @Override
    public TemporaryToken create(String token, User user) {
        TemporaryToken key = new TemporaryToken();
        key.setUserId(user);
        key.setToken(token);
        key.setIpAddress(getIpAdress());
        key.setExpiredDate(new Date(System.currentTimeMillis() + 1800000));
        return temporaryTokenRepository.save(key);
    }

    @Override
    public TemporaryToken reverseToken(String token) {
        TemporaryToken refreshToken = temporaryTokenRepository.findByToken(token);
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1800000));
        return temporaryTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<TemporaryToken> findTokenAndExpiredDate(String token) {
        return temporaryTokenRepository.findByTokenAndExpiredDateIsBefore(token, new Date());
    }

    @Override
    public TemporaryToken remove(User user) {
        return null;
    }
}
