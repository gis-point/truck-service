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

    private String truck;

    private String driver;

    private String trailerNumber;

    private String addressLineFrom;

    private String cityFrom;

    private String stateFrom;

    private String timeFrom;

    private String addressLineTo;

    private String cityTo;

    private String stateTo;

    private String timeTo;

    private String status;

}