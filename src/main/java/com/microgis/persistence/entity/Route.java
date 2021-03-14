package com.microgis.persistence.entity;

import com.microgis.persistence.dto.RouteVehicleType;
import com.microgis.util.Constants;
import com.microgis.util.DateTimeAsTimestampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "MG_ROUTE")
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "active")
    private boolean active;

    @Column(name = "color", columnDefinition = "VARCHAR(25)")
    private String color;

    @Column(name = "activation")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime activation;

    @Column(name = "deactivation")
    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime deactivation;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "SAVE_EVENT")
    private boolean saveEventHistory;

    @Column(name = "SPEED_LIMIT")
    private double speedLimit;

    @Column(name = "VOYAGE_AUTO")
    private boolean voyageAutoCreate;

    @Column(name = "CIRCLE_DELAY")
    private float circleDelayPercent; // delay percent for all circle

    @Column(name = "AWAIT_TIME")
    private float awaitTimePercent; // awaitTimePercent If the bus is late, we are allowed to start voyage from any stop after the time n% circle

    @Column(name = "VERSION")
    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    private AccountLightweight account;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutePoint> routePoints;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "TRAFFIC_ID")
    private GeoZoneLightweight traffic;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MG_ROUTE_HAS_GROUP", joinColumns = @JoinColumn(name = "ROUTE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<Group> groups;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private DateTime createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFY_DATE")
    private DateTime lastModifyDate;

    @Column(name = "VEHICLE_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private RouteVehicleType vehicleType;

    @Column(name = "PUBLIC_URL")
    private String publicUrl;

    @Column(name = "TEXT_COLOR", columnDefinition = "VARCHAR(25)")
    private String textColor;

    @Column(name = "WHEELCHAIR_PLACE")
    private boolean wheelchairPlace;

    @Column(name = "BIKES_ALLOWED")
    private boolean bikesAllowed;

    @Column(name = "ACTUAL_WAYPOINTS")
    private String actualWaypoints;

    @Column(name = "COORDINATES")
    private String coordinates;

    @Column(name = "INPUT_WYPOINTS")
    private String inputWaypoints;

    @Column(name = "SUMMARY")
    private String summary;

}