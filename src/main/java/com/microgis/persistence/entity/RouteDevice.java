package com.microgis.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RouteDevice extends RoutePoint {

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DeviceLightweight.class)
    @JoinColumn(name = "device_id", referencedColumnName = "ID")
    private DeviceLightweight device;

    @Override
    public String getName() {
        DeviceLightweight deviceLightweight = getDevice();
        if (deviceLightweight == null) {
            return "[DELETED DEVICE]";
        } else {
            return deviceLightweight.getLicensePlate();
        }
    }

}