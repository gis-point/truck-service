package com.microgis.persistence.entity;


import com.microgis.persistence.dto.Status;
import com.microgis.util.Constants;
import com.microgis.util.converter.DateTimeAsTimestampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Driver")
@NoArgsConstructor
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String address = "";

    private String badgeID = "";

    @Column(name = "BIRTH_DATE")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime birthDate = DateTime.now();

    private String contactEmail = "";

    private String contactPhone = "";

    @Column(name = "ACTIVE")
    private boolean active;

    private boolean mobile;

    private String login = "";

    private String pass = "";

    private String refreshToken = "";

    @Column(name = "CREATION_TS")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN_WITH_SECOND)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime creationTime;

    private String description = "";

    private String deviceID = "";

    private String displayName = "";

    private String driverClass = "";

    private String driverCode = "";

    @Column(name = "employeeStatus")
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRIVER;

    @Column(name = "LICENSE_DATE")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime licenseStart = DateTime.now();

    @Column(name = "LICENSE_EXPIRE")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime licenseExpire = DateTime.now();

    private String licenseNumber = "";

    private String licenseType = "";

    private String notes = "";

    private String orderNumber = "";

    private String passportNumber = "";

    private String personnelNumber = "";

    private byte[] photo;

    private String photoFormat;

    @Column(name = "WORK_START")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime workStart = DateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountLightweight account;

    @ManyToMany
    @JoinTable(name = "MG_GROUP_HAS_EMPLOYEE",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<Group> groups;

}