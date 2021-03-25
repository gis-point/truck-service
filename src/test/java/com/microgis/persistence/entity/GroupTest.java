package com.microgis.persistence.entity;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class GroupTest {

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
        Driver driverRed = new Driver();
        driverRed.setPass("driverRed");
        Driver driverBlack = new Driver();
        driverBlack.setPass("driverBlack");
        DeviceLightweight deviceLightweightRed = new DeviceLightweight();
        deviceLightweightRed.setAccountCode("deviceLightweightRed");
        DeviceLightweight deviceLightweightBlack = new DeviceLightweight();
        deviceLightweightBlack.setAccountCode("deviceLightweightBlack");
        EqualsVerifier.forClass(Group.class)
                .withPrefabValues(AccountLightweight.class, accountLightweightRed, accountLightweightBlack)
                .withPrefabValues(Group.class, groupRed, groupBlack)
                .withPrefabValues(Driver.class, driverRed, driverBlack)
                .withPrefabValues(DeviceLightweight.class, deviceLightweightRed, deviceLightweightBlack)
                .suppress(Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(Group.class).verify();
    }
}