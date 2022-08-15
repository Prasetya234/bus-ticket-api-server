package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.WalletDto;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.repository.UserRepository;
import com.bus.ticket.web.repository.WalletRepository;
import com.bus.ticket.web.service.HistoryBalanceService;
import com.bus.ticket.web.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private HistoryBalanceService historyBalanceService;

    @Autowired
    public WalletServiceImpl(UserRepository userRepository, WalletRepository walletRepository, HistoryBalanceService historyBalanceService) {
        this.userRepository = userRepository;
        this.historyBalanceService = historyBalanceService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    @Override
    public Wallet add(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User ID Tidak Di Temukan" + userId));
        if (walletRepository.findByUserId(user.getId()).isPresent()) {
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