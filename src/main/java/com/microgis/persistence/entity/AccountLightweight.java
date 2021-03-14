package com.microgis.persistence.entity;

import com.microgis.persistence.dto.TimeZoneList;
import com.microgis.util.Constants;
import com.microgis.util.DateTimeConverter;
import com.microgis.util.TimeZoneListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountLightweight {

    @Column(name = "isActive")
    boolean active;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "DEFAULT_GROUP_ID")
    private Group defaultGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID")
    private int id;

    @Column(name = "accountID")
    private String code;

    @Column(name = "contactName")
    private String contactName = "Андрій";

    @Column(name = "notifyEmail")
    private String notifyEmail = "support@ua-gis.com";

    @Column(name = "contactEmail")
    private String contactEmail = "support@ua-gis.com";

    @Column(name = "contactPhone")
    private String contactPhone = "+380676723905";

    @Column(name = "description")
    private String description = "";

    @Column(name = "multiAccount")
    private boolean multiAccount = false;

    @Column(name = "privateLabelName")
    private String labelName = "";

    @Column(name = "balanceNow")
    private int balance = 0;

    @Column(name = "password")
    private String password = "MicroGISka167011";

    @Column(name = "lastBalanceInc")
    private int lastBalanceIncrement;

    @Column(name = "timeZone")
    @Convert(converter = TimeZoneListConverter.class)
    private TimeZoneList zone = TimeZoneList.EUROPE_KIEV;

    @Column(name = "smsProperties")
    private String smsProperties = "";

    @Column(name = "notes")
    private String notes = "";

    @Column(name = "maximumDevices")
    private Integer maximumDevices = 0;

    @Column(name = "passwdChangeTime")
    @DateTimeFormat(pattern = Constants.PATTERN_DATE_TIME)
    @Convert(converter = DateTimeConverter.class)
    private DateTime passwdChangeTime;

    @Column(name = "creationTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime creation;

    @Column(name = "expirationTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime expired;

    @Column(name = "lastLoginTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime lastLogin;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Group> groups;

    @Column(name = "ADDRESS_GEOCODING_SERVER")
    private String addressGeocodingServer = "";

    @Column(name = "API_KEY_GEOCODING_SERVER")
    private String apiKeyGeocodingServer = "";

    @Column(name = "ADDRESS_ROUTING_SERVER")
    private String addressRoutingServer = "";

    @Column(name = "API_KEY_ROUTING_SERVER")
    private String apiKeyRoutingServer = "";

    @Column(name = "ADDRESS_TRANSIT_PREDICTION_SERVER")
    private String addressTransitPredictionServer = "";

    @Column(name = "API_KEY_TRANSIT_PREDICTION_SERVER")
    private String apiKeyTransitPredictionServer = "";

    @Column(name = "CONFIGURATION")
    private String configuration = "";

    @Column(name = "MEDICAL_EXAMINATION_TIME")
    private Integer medicalExaminationTime = 5;

    @Column(name = "VOYAGE_SERVICE_TIME")
    private Integer voyageServiceTime = 20;

    @Column(name = "DEVICE_DEPOSITING_TIME")
    private Integer deviceDepositingTime = 5;

    @Column(name = "COMPANY_ID")
    private String companyId;

    @Column(name = "WORKSESSION_CHANGE_ON_LINE_TIME")
    private Integer worksessionChangeOnLineTime = 3;

    @OneToMany
    @JoinColumn(name = "MULTI_ACCOUNT_ID")
    private List<Group> connectedGroups;

    @Column(name = "GTFS_URL")
    private String gtfsUrl;

    @Column(name = "LANG")
    private String lang;

    @Column(name = "PUBLIC_PHONE")
    private String publicPhoneNumber;

    @Column(name = "PUBLIC_EMAIL")
    private String publicEmail;

    @Column(name = "PUBLIC_FARE_URL")
    private String publicFareUrl;

}