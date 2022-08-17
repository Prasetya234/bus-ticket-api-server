package com.bus.ticket.web.service;

import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;

public interface OtpService {
    CodeOtp create(User user);
}
