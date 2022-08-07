package com.bus.ticket.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "temporary_token")
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryToken {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
