package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.dto.TopUpDTO;
import com.bus.ticket.web.model.HistoryBalance;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.repository.HistoryBalanceRepository;
import com.bus.ticket.web.service.HistoryBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class HistoryBalanceServiceImpl implements HistoryBalanceService {

    private HistoryBalanceRepository historyBalanceRepository;
    private AuthenticationFacade facade;

    @Autowired
    public HistoryBalanceServiceImpl(HistoryBalanceRepository historyBalanceRepository, AuthenticationFacade facade) {
        this.historyBalanceRepository = historyBalanceRepository;
        this.facade = facade;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<HistoryBalance> showHistoryBalance(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return historyBalanceRepository.findByUserId(facade.getAuthentication(), paging);
    }

    @Transactional(readOnly = true)
    @Override
    public HistoryBalance findById(String id) {
        return historyBalanceRepository.findById(id).orElseThrow(() -> new NotFoundException("History Balace ID tidak di ketahui"));
    }

    @Transactional
    @Override
    public HistoryBalance addBalanceHistory(Wallet wallet, User user, TopUpDTO topUpDTO) {
        HistoryBalance balance = new HistoryBalance();
        balance.setWallet(wallet);
        balance.setUserId(user);
        balance.setDescription(topUpDTO.getDescription());
        balance.setToBank(topUpDTO.getToBank());
        balance.setAdmissionFee(topUpDTO.getSaldo());
        balance.setMethodPayment(topUpDTO.getMethodPayment());
        return historyBalanceRepository.save(balance);
    }

    @Transactional
    @Override
    public HistoryBalance createHistoryFirst(User user, Wallet wallet) {
        HistoryBalance create = new HistoryBalance();
        create.setDescription("First Join Member");
        create.setAdmissionFee(1000F);
        create.setToBank("");
        create.setMethodPayment("E-Wallet_Gopay");
        create.setUserId(user);
        create.setWallet(wallet);
        return historyBalanceRepository.save(create);
    }
}
