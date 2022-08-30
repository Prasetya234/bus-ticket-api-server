package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping
    public CommonResponse<User> updateData(@RequestBody UserDto userDto) {
        return ResponseHelper.successResponse(userService.update(userDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public CommonResponse<User> getUser() {
        return ResponseHelper.successResponse(userService.getUserInitial());
    }
}
