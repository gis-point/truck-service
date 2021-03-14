package com.microgis.persistence.entity;


import com.microgis.util.Constants;
import com.microgis.util.DateTimeAsTimestampConverter;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The persistent class for the MG_ROUTE_SCHEDULE database table.
 */
@Data
@Entity
@Table(name = "MG_SCHEDULE")
public class Schedule {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Size(max = 2)
    @Column(name = "ORDINAL")
    private String ordinal;

    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime activation;

    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    @Convert(converter = DateTimeAsTimestampConverter.class)
    private DateTime deactivation;

    @Column
    private boolean active;

    @Column(name = "auto_voyage_create")
    private boolean autoVoyageCreate;

    /**
     * Not show complete voyage on time line
     */
    @Column(name = "DELETE_COMPETED_VOYAGE")
    private boolean notShowCompetedVoyage;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GLOBAL_DEPARTURE")
    private boolean globalDispatch;

    @Column(name = "voyage_name_pattern")
    private String voyageNamePattern;

    @Column(name = "DURATION")
    private String duration;

    @Column(name = "ALL_START")
    private String scheduleStart;

    @Column(name = "ALL_FINISH")
    private String scheduleFinish;

    @Column(name = "interval1_active")
    private boolean interval1Active;

    @Column(name = "interval2_active")
    private boolean interval2Active;

    @Column(name = "interval1_finish")
    private String interval1Finish;

    @Column(name = "interval1_start")
    private String interval1Start;

    @Column(name = "interval2_finish")
    private String interval2Finish;

    @Column(name = "interval2_start")
    private String interval2Start;

    @Column(name = "limited_month")
    private boolean limitedMonth;

    @Column(name = "limited_time")
    private boolean limitedTime;

    @Column(name = "RESTRICTION_DEAD_DENSE")
    private int restrictionDeadDense;

    @Basic(optional = false)
    @Column(name = "MASK_MONTH")
    private Integer month;

    @Basic(optional = false)
    @Column(name = "MASK_DAYS_MONTH")
    private Integer monthDays;

    @Basic(optional = false)
    @Column(name = "MASK_DAYS_WEEK")
    private Integer weekDays;

    @Column(name = "version")
    @Version
    private int version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "ID")
    private AccountLightweight account;

    //bi-directional many-to-one association to SchedulePoint
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SchedulePoint> schedulePoints;

    //bi-directional many-to-one association to Route
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

    @ManyToMany
    @JoinTable(name = "MG_SCHEDULE_HAS_GROUP", joinColumns = @JoinColumn(name = "SCHEDULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<Group> groups;

    @Column(name = "WORKSESSION_NUMBER")
    private Integer worksessionNumber;

    @Column(name = "START_DINNER")
    private String startDinner;

    @Column(name = "END_DINNER")
    private String endDinner;

    @Column(name = "POINT_COUNT")
    private int pointCount;

}