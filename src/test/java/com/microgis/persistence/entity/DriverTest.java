package com.microgis.persistence.entity;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class DriverTest {

    @Test
    void testEquals() {
        AccountLightweight accountLightweightRed = new AccountLightweight();
        accountLightweightRed.setAddressGeocodingServer("accountLightweightRed");
        AccountLightweight accountLightweightBlack = new AccountLightweight();
        accountLightweightBlack.setAddressGeocodingServer("accountLightweightBlack");
        Group groupRed = new Group();
        groupRed.setCode("groupRed");
        Group groupBlack = new Group();
        groupBlack.setCode("groupBlack");
        DeviceLightweight deviceLightweightRed = new DeviceLightweight();
        deviceLightweightRed.setAccountCode("deviceLightweightRed");
        DeviceLightweight deviceLightweightBlack = new DeviceLightweight();
        deviceLightweightBlack.setAccountCode("deviceLightweightBlack");
        Driver driverRed = new Driver();
        driverRed.setPass("driverRed");
        Driver driverBlack = new Driver();
        driverBlack.setPass("driverBlack");
        EqualsVerifier.forClass(Driver.class)
                .withPrefabValues(AccountLightweight.class, accountLightweightRed, accountLightweightBlack)
                .withPrefabValues(Group.class, groupRed, groupBlack)
                .withPrefabValues(DeviceLightweight.class, deviceLightweightRed, deviceLightweightBlack)
                .withPrefabValues(Driver.class, driverRed, driverBlack)
                .suppress(Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(Driver.class).verify();
    }

}