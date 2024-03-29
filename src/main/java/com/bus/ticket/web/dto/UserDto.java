package com.bus.ticket.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String numberPhone;
    private Date dateOfBirth;
    private String address;
}
