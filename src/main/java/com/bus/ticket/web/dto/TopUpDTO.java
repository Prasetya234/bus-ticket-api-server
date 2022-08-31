package com.bus.ticket.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopUpDTO {
    private float saldo;
    private String toBank;
    private String description;
    private String methodPayment;
}
