package com.bus.ticket.web.service;

import com.bus.ticket.web.model.HistoryJoinAdmin;
import com.bus.ticket.web.model.User;

public interface HistoryJoinAdminService {
    HistoryJoinAdmin join(User user, String description);
}
