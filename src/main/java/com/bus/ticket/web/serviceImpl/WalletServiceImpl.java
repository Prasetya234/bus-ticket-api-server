package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.WalletDto;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.repository.UserRepository;
import com.bus.ticket.web.repository.WalletRepository;
import com.bus.ticket.web.service.HistoryBalanceService;
import com.bus.ticket.web.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private HistoryBalanceService historyBalanceService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, HistoryBalanceService historyBalanceService) {
        this.historyBalanceService = historyBalanceService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    @Override
    public Wallet add(User user) {
        if (walletRepository.findByUserId(user).isPresent()) {
            throw new NotFoundException("Wallet already created");
        }
        Wallet wallet = new Wallet();
        wallet.setSaldo(1000);
        wallet.setUserId(user);
        Wallet done = walletRepository.save(wallet);
        historyBalanceService.createHistoryFirst(user, done);
        return done;
    }

    @Override
    public Wallet balanceManipulation(Float nominal, boolean out) {
        return null;
    }

}
