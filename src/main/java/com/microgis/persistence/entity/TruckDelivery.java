package com.microgis.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TRUCK_DELIVERY_INFO")
@NoArgsConstructor
@AllArgsConstructor
public class TruckDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer loadNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "truckId", referencedColumnName = "id")
    private DeviceLightweight truck;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driverId", referencedColumnName = "id")
    private Driver driver;

    private String trailerNumber;

    private String trailerPlateNumber;

    private String addressLineFrom;

    private String cityFrom;

    private String stateFrom;

    private String timeFrom;

    private String addressLineTo;

    private String cityTo;

    private String stateTo;

    private String timeTo;

    private String status;

    private Integer pallets;

    private Integer weight;

    private String cargoType;

    private String brokerCompany;

    private String brokerAddress;

    private String brokerPhone;

    private String brokerPhoneExtension;

    private String brokerName;

}