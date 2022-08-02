package com.bus.ticket.web.model;

import com.bus.ticket.enggine.auditing.DateConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promo")
public class Promo extends DateConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "code", nullable = false)
    private String code;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;

    @Column(name = "max_use", nullable = false)
    private int maxUse;

    @Column(name = "used")
    private int used;

    @Column(name = "all_company")
    private boolean allCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}
