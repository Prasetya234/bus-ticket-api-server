package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.UserRepository;
import com.bus.ticket.web.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User create(UserDto userDto) {
        return null;
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public User update(UserDto userDto) {
        return null;
    }
}
