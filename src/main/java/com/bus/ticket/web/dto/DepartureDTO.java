package com.bus.ticket.web.dto;

import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.DepartureStatus;
import com.bus.ticket.web.model.Destination;
import com.bus.ticket.web.model.Promo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DepartureDTO {
    private float price;
    private String description;
    private int totalWindow;
    private String startLocation;
    private String endLocation;
    private int passengerLimit;
    private LocalTime startHoursOfDeparture;
    private LocalTime endHoursOfDeparture;
    private int travelingTimeHours;
    private int travelingTimeMinute;
    private Date dateOfDeparture;
    private List<String> listPaymentType = new ArrayList<>();
    private List<String> listFacility = new ArrayList<>();
    private String companyId;
    private String promoId;
    private String startDestination;
    private String endDestination;
}
