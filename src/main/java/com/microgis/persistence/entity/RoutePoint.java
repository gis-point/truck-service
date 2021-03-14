package com.microgis.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the MG_ROUTE_OBJECT database table.
 */
@Data
@Entity
@Table(name = "MG_ROUTE_POINT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER, length = 1)
@NoArgsConstructor
@ToString(exclude = "geoZone")
@EqualsAndHashCode(exclude = "geoZone")
public abstract class RoutePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ORDINAL")
    private short ordinalNumber;

    @Column(name = "EP")
    private boolean edgePoint;

    @Column(name = "DISTANCES")
    private Double distance;

    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEOZONE_ID", referencedColumnName = "ID")
    private GeoZoneLightweight geoZone;

    @ManyToOne
    private Route route;

    @OneToMany(mappedBy = "routePoint",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<SchedulePoint> schedulePoints;

    public abstract String getName();

}