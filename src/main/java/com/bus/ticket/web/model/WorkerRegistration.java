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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker_registration")
public class WorkerRegistration extends DateConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "age")
    private int age;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "criminal_record")
    private String criminalRecord;

    @Column(name = "nik")
    private String nik;

    @Column(name = "ktp_photo_url")
    private String ktpPhotoUrl;

    @Column(name = "sim_photo_url")
    private String simPhotoUrl;

    @Column(name = "is_married")
    private boolean isMerried;

    @Column(name = "is_session_creation_policy")
    private boolean isSessionCreationPolicy;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User userId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="company_id")
    private Company companyId;
}
