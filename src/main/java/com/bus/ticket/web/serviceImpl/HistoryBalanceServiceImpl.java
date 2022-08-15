package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.web.model.HistoryBalance;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.repository.HistoryBalanceRepository;
import com.bus.ticket.web.service.HistoryBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class HistoryBalanceServiceImpl implements HistoryBalanceService {

    private HistoryBalanceRepository historyBalanceRepository;

    @Autowired
    public HistoryBalanceServiceImpl(HistoryBalanceRepository historyBalanceRepository) {
        this.historyBalanceRepository = historyBalanceRepository;
    }

    @Transactional
    @Override
    public HistoryBalance createHistoryFirst(User user, Wallet wallet) {
        HistoryBalance create = new HistoryBalance();
        create.setDescription("First Join Member");
        create.setAdmissionFee(1000F);
        create.setMethodPayment("1");
        create.setUserId(user);
        create.setWallet(wallet);
        return historyBalanceRepository.save(create);
    }
}
