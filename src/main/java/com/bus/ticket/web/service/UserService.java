package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.LoginDto;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import freemarker.template.TemplateException;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserService {
    CodeOtp create(UserDto userDto) throws TemplateException, MessagingException, IOException;
    Page<User> findAll(int page, int size);
    User update(UserDto userDto);
    TemporaryToken authorities(LoginDto loginDto);
    User active(String userId, String otp);
    CodeOtp resendCodeOtp(String userId);
}
