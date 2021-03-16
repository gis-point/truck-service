package com.microgis.persistence.entity;

import com.microgis.util.converter.DateTimeConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Device")
public class DeviceLightweight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID")
    private int id;

    @Column(name = "lastValidLongitude")
    private double longitude;

    @Column(name = "lastValidLatitude")
    private double latitude;

    @Column(name = "lastValidSpeedKPH")
    private double speed;

    @Column(name = "lastValidHeading")
    private double heading;

    @Column(name = "lastValidEventTimestamp")
    @Convert(converter = DateTimeConverter.class)
    private DateTime lastValidEventTimestamp;

    @Column(name = "deviceID")
    private String legacyDeviceId;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "equipmentType")
    private String equipmentType;

    @Column(name = "deviceColor")
    private String deviceColor;

    @Column(name = "f_LastFuelLevel")
    private double fuelLevel;

    @Column(name = "speedLimitKPH")
    private double speedLimitKPH;

    @Column(name = "rptLimitSpeedCity")
    private double rptLimitSpeedCity;

    @Column(name = "lastEventlatitude")
    private double lastLatitude;

    @Column(name = "lastEventlongitude")
    private double lastLongitude;

    @Column(name = "lastValidAltitude")
    private double altitude;

    @Column(name = "rptDayEngineHR")
    private double rptDayEngineHR;

    @Column(name = "rptFactorDist")
    private double rptFactorDist = 1;

    @Column(name = "rptTurboToArmed")
    private double rptTurboToArmed;

    @Column(name = "countGprsTraffic")
    private double countGprsTraffic;

    @Column(name = "lastGPSOdomKM")
    private double lastGPSOdomKM;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountLightweight account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private GeoZoneLightweight lastGeozone;

    @Column(name = "accountID")
    private String accountCode;

    @Column(name = "lastEventTimestamp")
    @Convert(converter = DateTimeConverter.class)
    private DateTime lastEventTime;

    @Column(name = "lastStartTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime lastMotionTime;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "MG_GROUP_HAS_DEVICE",
            joinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"))
    private List<Group> groups;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "VOYAGE_ID")
    private Voyage voyage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEFAULT_GROUP_ID")
    private Group defaultGroup;

    @Column(name = "notes")
    private String notes;

    @Column(name = "uniqueID")
    private String uniqueCode;

    @Column(name = "deviceCode")
    private String protocolCode;

    @Column(name = "codeVersion")
    private String protocolVersion;

    @Column(name = "vehicleID")
    private String vehicleCode;

    @Column(name = "maintIntervalKM0")
    private double maintIntervalKM0 = 0;

    @Column(name = "maintOdometerKM0")
    private double maintOdometerKM0 = 0.0;

    @Column(name = "maintIntervalKM1")
    private double maintIntervalKM1 = 0;

    @Column(name = "maintOdometerKM1")
    private double maintOdometerKM1 = 0.0;

    @Column(name = "maintIntervalHR0")
    private double maintIntervalHR0 = 0;

    @Column(name = "maintEngHoursHR0")
    private double maintEngHoursHR0 = 0;

    @Column(name = "imeiNumber")
    private String imei;

    @Column(name = "simPhoneNumber")
    private String phone;

    @Column(name = "leasedSimPhoneNumber")
    private boolean leasedSimPhoneNumber;

    @Column(name = "additionalPhoneNumber")
    private String additionalPhoneNumber;

    @Column(name = "leasedAdditionalPhoneNumber")
    private boolean leasedAdditionalPhoneNumber;

    @Column(name = "serialNumber")
    private String serial;

    @Column(name = "smsEmail")
    private String email;

    @Column(name = "shopStatusList")
    private String shopStatuses;

    @Column(name = "isActive")
    private boolean active;

    @Column(name = "elapsedTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime expire;

    @Column(name = "creationTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime creation;

    @Column(name = "lastUpdateTime")
    @Convert(converter = DateTimeConverter.class)
    private DateTime update;

    @Column(name = "recordEvent")
    private boolean recordEvent;

    @Column(name = "deviceIcon")
    private String icon;

    @Column(name = "filterDataOn")
    private boolean filterData = true;

    @Column(name = "filterSkipNonValid")
    private boolean filterInvalid = true;

    @Column(name = "filterMaxSpeed")
    private double filterMaxSpeed = 160;

    @Column(name = "filterMinSatt")
    private int filterMinSatellites = 3;

    @Column(name = "filterMaxHDOP")
    private double filterMaxHDOP = 2;

    @Column(name = "tripMinSatt")
    private int movingMinSatellites = 3;

    @Column(name = "tripMaxSpeed")
    private int movingMinSpeed = 3;

    @Column(name = "tripGPScorrOn")
    private boolean movingGPScorrOn = true;

    @Column(name = "tripMinStop")
    private int movingMaxStopDuration = 300;

    @Column(name = "tripMinDis")
    private int movingMinDistance = 20;

    @Column(name = "tripMaxEventsDist")
    private int movingMaxDistanceBetweenMessages = 10000;

    @Column(name = "tripMinTime")
    private int tripMinDuration = 60;

    @Column(name = "MTD_MIN_TRIP_DISTANCE")
    private int tripMinDistance = 1000;

    @Column(name = "passengers")
    private int passengers;

    @Column(name = "classiness")
    private String classiness;

    @Column(name = "manufactureDate")
    private String manufactureDate;

    @Column(name = "composition")
    private String composition;

    @Column(name = "tTimer")
    private boolean turboTimer;

    @Column(name = "countDays")
    private int countDays;

    @Column(name = "countNotes")
    private String countNotes;

    @Column(name = "workOrderID")
    private String workOrderID;

    @Column(name = "lastEngineHours")
    private double lastEngineHours = 0;

    @Column(name = "f_InTank")
    private double fuelInTank = 0;

    @Column(name = "f_FreeRunningOn")
    private double fuelFreeRunningOn;

    @Column(name = "f_CityMooving")
    private double fuelCityMooving;

    @Column(name = "lastSatelliteCount")
    private int satelliteCount;

    @Column(name = "f_CountryMooving")
    private double fuelCountryMooving;

    @Column(name = "f_FactorCapacity")
    private double fuelFactorCapacity;

    @Column(name = "fuelNorms")
    private double fuelNormsSummer;

    @Column(name = "f_FactorWinter")
    private double fuelNormsWinter;

    @Column(name = "FUEL_WINTER")
    private Integer fuelWinter = 1101;

    @Column(name = "FUEL_SUMMER")
    private Integer fuelSummer = 401;

    @Column(name = "fuelNormsHour")
    private boolean fuelNormsHour;

    @Column(name = "f_ChangeExcepMath")
    private boolean fuelChangeExcepMath;

    @Column(name = "f_maxImpuls")
    private Integer fuelMaxImpuls;

    @Column(name = "f_PassZeroValue")
    private boolean fuelPassZeroValue;

    @Column(name = "parameterList")
    private String parameterList = "";

    @Column(name = "FUEL_SENSOR_DEFAULT")
    private Integer methodSensorId;

    @Column(name = "FUEL_PULSE_DEFAULT")
    private Integer methodPulseId;

    @Column(name = "FUEL_ABSOLUTE_DEFAULT")
    private Integer methodAbsoluteId;

    @Column(name = "FUEL_INSTANT_DEFAULT")
    private Integer methodInstantId;

    @Column(name = "DEVICE_ACCESS_CODE")
    private String accessCode;

    @Column(name = "DEVICE_CONTROL_NOTE")
    private String controlNotes;

    @Column(name = "lastStatusCode")
    private Integer statusCode = 0;

    @Column(name = "lastAddress")
    private String address;

    @Column(name = "activeZone")
    private String geozone;

    @Column(name = "DATA_SENSORS")
    private String sensorData;

    @Column(name = "DATA_SENSORS_RAW")
    private String rawData;

    @Column(name = "DATA_HDOP")
    private double hdop;

    @Column(name = "CONSTANT_TRACK_COLOR")
    private String trackColor = "#ff0000";

    @Column(name = "TRACK_COLOR_SENSOR")
    private Integer trackColorSensor;

    @Column(name = "TRACK_WIDTH")
    private Integer trackWidth = 1;

    @Column(name = "FUEL_CORECTION")
    private double fuelCorection = 0.0;


    @Column(name = "FUEL_CORECTION_EVENT")
    private Integer fuelCorectionEvent;

    @Column(name = "HANDICAPPED")
    private boolean handicapped;

    @Column(name = "WIFI")
    private boolean wifi;

}