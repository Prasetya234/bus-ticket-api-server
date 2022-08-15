package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User create(UserDto userDto);
    Page<User> findAll(int page, int size);
    User update(UserDto userDto);
}
