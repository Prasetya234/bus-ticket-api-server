package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.utils.Utils;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.TemporaryTokenRepository;
import com.bus.ticket.web.service.TemporaryTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class TemporaryTokenServiceImpl extends Utils implements TemporaryTokenService  {

    TemporaryTokenRepository temporaryTokenRepository;

    @Autowired
    public TemporaryTokenServiceImpl(TemporaryTokenRepository temporaryTokenRepository) {
        this.temporaryTokenRepository = temporaryTokenRepository;
    }

    @Transactional
    @Override
    public TemporaryToken create(String token, User user) {
        TemporaryToken key = new TemporaryToken();
        key.setUserId(user);
        key.setToken(token);
        key.setIpAddress(getIpAdress());
        key.setExpiredDate(new Date(System.currentTimeMillis() + 1800000));
        return temporaryTokenRepository.save(key);
    }

    @Transactional
    @Override
    public TemporaryToken reverseToken(String token) {
        TemporaryToken refreshToken = temporaryTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("Token not found"));
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1800000));
        return temporaryTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TemporaryToken> findTokenAndExpiredDate(String token) {
        return temporaryTokenRepository.findByTokenValid(token, new Date());
    }

    @Override
    public TemporaryToken remove(User user) {
        return null;
    }
}
