package com.bus.ticket.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private String logo;
    private String name;
    private String director;
    private int numberOfBuses;
    private String phoneNumber;
    private String address;
}
