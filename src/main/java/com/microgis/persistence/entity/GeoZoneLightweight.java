package com.microgis.persistence.entity;

import com.microgis.persistence.dto.GeoZoneType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "MG_GEOZONE")
public class GeoZoneLightweight {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Id
    private int id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "description")
    private String name;

    @Column(name = "shapeColor")
    private String shapeColor;

    @Column(name = "iconPoiPath")
    private String iconPoiPath;

    @Column(name = "geofenceAddress")
    private String address;

    @Column(name = "newbie")
    private boolean newbie;

    @Column(name = "radius")
    private int radius;

    @Column(name = "priority")
    private int priority;

    @Column(name = "geom")
    private String geom;

    @Column(name = "zoneType")
    @Enumerated(value = EnumType.ORDINAL)
    private GeoZoneType type;

    @Column(name = "speedLimitKPH")
    private int speedLimit;

    @Column(name = "visibleZoomFrom")
    private int visibleZoomFrom;

    @Column(name = "visibleZoomTo")
    private int visibleZoomTo;

    @Column(name = "sectorEnterFrom")
    private int sectorEnterFrom;

    @Column(name = "sectorEnterTo")
    private int sectorEnterTo;

    @Column(name = "showPoi")
    private boolean showPoi;

    @Column(name = "reverseGeocode")
    private boolean sourceAddress;

    @Column(name = "arrivalZone")
    private boolean arrivalZone;

    @Column(name = "departureZone")
    private boolean departureZone;

    @Column(name = "area")
    private double area;

    @Column(name = "perimeter")
    private double perimeter;

    @Column(name = "notes")
    private String notes;

    @Column(name = "minLatitude")
    private double minLat;

    @Column(name = "maxLatitude")
    private double maxLat;

    @Column(name = "minLongitude")
    private double minLng;

    @Column(name = "maxLongitude")
    private double maxLng;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "ID")
    private AccountLightweight account;

    @ManyToMany
    @JoinTable(name = "MG_DEVICE_GROUP_IN_GEOZONE",
            joinColumns = @JoinColumn(name = "geozone_id"),
            inverseJoinColumns = @JoinColumn(name = "deviceGroup_id"))
    private List<Group> groups;

    @Column(name = "zoneProperty")
    private int property = 5;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private DateTime createdDate;

}