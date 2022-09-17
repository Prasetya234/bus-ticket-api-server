package com.bus.ticket.web.model;


import com.bus.ticket.enggine.auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departure")
public class Departure extends DateConfig {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "total_window")
    private int totalWindow;

    @Column(name = "start_location", nullable = false)
    private String startLocation;

    @Column(name = "end_location", nullable = false)
    private String endLocation;

    @Column(name = "passenger_limit")
    private int passengerLimit;

    @Column(name = "passenger_total")
    private int passengerTotal;

    @Lob
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "payment_type_list", nullable = false)
    private String paymentTypeList;

    @Lob
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "facility_list")
    private String facilityList;

    @Column(name = "start_hours_of_departure")
    private LocalTime startHoursOfDeparture;

    @Column(name = "end_hours_of_departure")
    private LocalTime endHoursOfDeparture;

    @Column(name = "traveling_time_hours")
    private int travelingTimeHours;

    @Column(name = "traveling_time_minute")
    private int travelingTimeMinute;

    @Column(name = "date_of_departure")
    private Date dateOfDeparture;

    @ToString.Exclude
    @Transient
    private List<String> listPaymentType = new ArrayList<>();

    @ToString.Exclude
    @Transient
    private List<String> listFacility = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_bus")
    private CompanyEmploye driverBus;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_status_id")
    private DepartureStatus departureStatusId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_id")
    private Promo promoId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company companyId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_destination")
    private Destination startDestination;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_destination")
    private Destination endDestination;
}
