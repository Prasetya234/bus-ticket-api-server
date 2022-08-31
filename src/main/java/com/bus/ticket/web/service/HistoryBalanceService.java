package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.TopUpDTO;
import com.bus.ticket.web.model.HistoryBalance;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import org.springframework.data.domain.Page;

public interface HistoryBalanceService {

    Page<HistoryBalance> showHistoryBalance(int page, int size);
    HistoryBalance findById(String id);
    HistoryBalance addBalanceHistory(Wallet wallet, User user, TopUpDTO topUpDTO);
    HistoryBalance createHistoryFirst(User user, Wallet wallet);
}
