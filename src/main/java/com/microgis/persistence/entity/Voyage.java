package com.microgis.persistence.entity;

import com.microgis.persistence.dto.VoyageStatus;
import com.microgis.util.DateTimeAsTimestampConverter;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MG_VOYAGE")
public class Voyage {

    @Column(name = "ADDITIONAL_DURATION")
    private Duration additionalDuration;

    @Column(name = "STATUS")
    private VoyageStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountLightweight account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    private DeviceLightweight device;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @Column(name = "APPOINTMENT_TIME", insertable = false, updatable = false)
    private DateTime appointmentTime;

    @Column(name = "COMPLETION_TIME")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime completionTime;

    @Column(name = "EXPIRED_TIME")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime expiredTime;

    @Column(name = "CURRENT_POINT_TIME")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime lastCurrentPointUpdateTime;

    @Column(name = "VERSION")
    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRIVER_ID", referencedColumnName = "ID")
    private Driver driver;

    @Column(name = "ACTIVATION")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime activation;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CURRENT_POINT")
    private int currentPointOrdinal;

    @Column(name = "TOTAL_POINT")
    private int totalPointCount;

    @Column(name = "NOTES")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "CURRENT_POI")
    private GeoZoneLightweight currentGeoPoint;

    @Column(name = "DEL_USER_ID")
    private Integer delUser;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private DateTime createdDate;

}