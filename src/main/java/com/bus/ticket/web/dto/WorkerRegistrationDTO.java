package com.bus.ticket.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerRegistrationDTO {
    private int age;
    private String address;
    private String description;
    private String criminalRecord;
    private String nik;
    private String ktpPhotoUrl;
    private String simPhotoUrl;
    private boolean isMerried;
    private boolean isSessionCreationPolicy;
    private String companyId;
}
