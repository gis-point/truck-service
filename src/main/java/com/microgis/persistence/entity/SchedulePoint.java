package com.microgis.persistence.entity;


import com.microgis.util.converter.DateTimeAsTimestampConverter;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * The persistent class for the MG_ROUTE_OBJECT_SCHEDULE database table.
 */
@Data
@Entity
@Table(name = "MG_SCHEDULE_POINT")
public class SchedulePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ARRIVAL")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime arrivalDateTime;

    @Column(name = "arrival_deflection")
    private String arrivalDeflection;

    @Column(name = "arrival_relative")
    private String arrivalRelative;

    @Column(name = "DEPARTURE")
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime departureDateTime;

    @Column(name = "DEPARTURE_DEFLECTION")
    private String departureDeflection;

    @Column(name = "DEPARTURE_RELATIVE")
    private String departureRelative;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_POINT_ID", referencedColumnName = "ID")
    private RoutePoint routePoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "ID")
    private Schedule schedule;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID")
    private Route route;

    @Column(name = "VERSION")
    @Version
    private int version;

    @Column(name = "ORDINAL")
    private short ordinalNumber;

    @Column(name = "CIRCLE")
    private short circle;

}