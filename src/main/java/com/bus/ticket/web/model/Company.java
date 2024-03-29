package com.bus.ticket.web.model;

import com.bus.ticket.enggine.auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class Company extends DateConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "logo")
    private String logo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name = "number_of_buses", nullable = false)
    private int numberOfBuses;

    @Column(name = "total_passenger")
    private int totalPassenger;

    @Column(name = "total_employees")
    private Integer totalEmployees;

    @Lob
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "blocked")
    private boolean blocked;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reting_id")
    private Reting retingId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private User adminId;

}
