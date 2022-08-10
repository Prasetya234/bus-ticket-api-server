package com.bus.ticket.web.model;


import com.bus.ticket.enggine.auditing.DateConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

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

    @Column(name = "payment_type_list", nullable = false)
    private String paymentTypeList;

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

    @Column(name = "departure_status_id")
    private int departureStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_id")
    private Promo promoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_destination")
    private Destination startDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_destination")
    private Destination endDestination;
}
