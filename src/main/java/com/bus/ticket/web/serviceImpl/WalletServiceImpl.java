package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.dto.TopUpDTO;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
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
    private AuthenticationFacade facade;
    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, HistoryBalanceService historyBalanceService, AuthenticationFacade facade) {
        this.historyBalanceService = historyBalanceService;
        this.walletRepository = walletRepository;
        this.facade = facade;
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

    @Transactional(readOnly = true)
    @Override
    public Wallet showWallet() {
        return walletRepository.findByUserId(facade.getAuthentication()).orElseThrow(() -> new NotFoundException("Dompet tidak ditemukan"));
    }

    @Transactional
    @Override
    public Wallet topup(TopUpDTO topUpDTO) {
        User user  = facade.getAuthentication();
        Wallet wallet = walletRepository.findByUserId(user).orElseThrow(() -> new NotFoundException("Dompet tidak ditemukan"));
        wallet.setSaldo(wallet.getSaldo() + topUpDTO.getSaldo());
        historyBalanceService.addBalanceHistory(wallet, user, topUpDTO);
        return walletRepository.save(wallet);
    }

    @Transactional
    @Override
    public Wallet balanceManipulation(Float nominal, boolean out) {
        Wallet wallet = walletRepository.findByUserId(facade.getAuthentication()).orElseThrow(() -> new NotFoundException("Dompet tidak ditemukan"));
        if (!out) {
            wallet.setSaldo(wallet.getSaldo()+nominal);
            return walletRepository.save(wallet);
        }
        Float price = wallet.getSaldo() - nominal;
        if (price < 0) throw new BussinesException("Uang tidak cukup");
        wallet.setSaldo(price);
        return walletRepository.save(wallet);
    }

}
