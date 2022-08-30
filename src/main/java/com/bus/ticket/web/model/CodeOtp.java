package com.bus.ticket.web.model;

import com.bus.ticket.enggine.auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "code_otp")
public class CodeOtp extends DateConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "code", nullable = false)
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;

    @Column(name = "used", nullable = false)
    private boolean used;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  User userId;
}
