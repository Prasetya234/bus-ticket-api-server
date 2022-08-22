package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user-role/{roleId}")
    public CommonResponse<User> createUser(@PathVariable(value = "roleId" ) Integer roleId, @RequestBody User user) {
        return ResponseHelper.successResponse(userService.createTeesting(roleId, user));
    }
}
