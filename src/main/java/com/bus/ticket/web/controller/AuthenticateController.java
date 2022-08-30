package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.LoginDto;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse<CodeOtp> signUp(@RequestBody UserDto user) throws TemplateException, MessagingException, IOException {
        return ResponseHelper.successResponse(userService.create(user));
    }
    @PostMapping("/signin")
    public CommonResponse<TemporaryToken> signIn(@RequestBody LoginDto user){
        return ResponseHelper.successResponse(userService.authorities(user));
    }
    @GetMapping("/user-active-code/{userId}")
    public CommonResponse<User> active(@PathVariable(name = "userId") String userId, @RequestParam(name = "code") String code) {
        return ResponseHelper.successResponse(userService.active(userId, code));
    }
    @GetMapping("/resend-active-code/{userId}")
    public CommonResponse<CodeOtp> resendActiveCode(@PathVariable(name = "userId") String userId) {
        return ResponseHelper.successResponse(userService.resendCodeOtp(userId));
    }
}
