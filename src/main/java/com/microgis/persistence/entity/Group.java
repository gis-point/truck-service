package com.microgis.persistence.entity;


import com.microgis.persistence.dto.GroupType;
import com.microgis.util.DateTimeConverter;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DeviceGroup")
public class Group {

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "groupInMultiGroup", joinColumns = @JoinColumn(name = "deviceGroupId"),
            inverseJoinColumns = @JoinColumn(name = "multiDeviceGroupId"))
    private List<Group> multiGroups;

    @ManyToMany(mappedBy = "multiGroups", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Group> localGroups;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Driver> drivers;

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<DeviceLightweight> devices;

    @ManyToMany(mappedBy = "groups")
    private List<GeoZoneLightweight> geoZones;

    @Column(name = "accountID")
    private String accountCode;

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "VERSION")
    @Version
    private int version;

    @Column(name = "groupID")
    private String code;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountLightweight account;

    @Column(name = "TYPE")
    @Enumerated(EnumType.ORDINAL)
    private GroupType type;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "creationTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime creationTime;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MULTI_ACCOUNT_ID")
    private AccountLightweight multiAccount;

}