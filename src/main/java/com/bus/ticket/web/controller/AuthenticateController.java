package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/v1/api/authenticate")
public class AuthenticateController {
    private UserService userService;

    @Autowired
    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public CommonResponse<User> signUp(@RequestBody UserDto user) throws TemplateException, MessagingException, IOException {
        return ResponseHelper.successResponse(userService.create(user));
    }
}
