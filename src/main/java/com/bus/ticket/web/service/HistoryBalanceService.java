package com.bus.ticket.web.service;

import com.bus.ticket.web.model.HistoryBalance;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;

public interface HistoryBalanceService {
    HistoryBalance createHistoryFirst(User user, Wallet wallet);
}
