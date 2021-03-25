package com.microgis.persistence.entity;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class DeviceLightweightTest {

    @Test
    void testEquals() {
        AccountLightweight accountLightweightRed = new AccountLightweight();
        accountLightweightRed.setAddressGeocodingServer("accountLightweightRed");
        AccountLightweight accountLightweightBlack = new AccountLightweight();
        accountLightweightBlack.setAddressGeocodingServer("accountLightweightBlack");
        DeviceLightweight deviceLightweightRed = new DeviceLightweight();
        deviceLightweightRed.setAccountCode("deviceLightweightRed");
        DeviceLightweight deviceLightweightBlack = new DeviceLightweight();
        deviceLightweightBlack.setAccountCode("deviceLightweightBlack");
        Driver driverRed = new Driver();
        driverRed.setPass("driverRed");
        Driver driverBlack = new Driver();
        driverBlack.setPass("driverBlack");
        Group groupRed = new Group();
        groupRed.setCode("groupRed");
        Group groupBlack = new Group();
        groupBlack.setCode("groupBlack");
        Voyage voyageRed = new Voyage();
        voyageRed.setGroup(groupRed);
        Voyage voyageBlack = new Voyage();
        voyageBlack.setGroup(groupBlack);
        EqualsVerifier.forClass(DeviceLightweight.class)
                .withPrefabValues(AccountLightweight.class, accountLightweightRed, accountLightweightBlack)
                .withPrefabValues(DeviceLightweight.class, deviceLightweightRed, deviceLightweightBlack)
                .withPrefabValues(Driver.class, driverRed, driverBlack)
                .withPrefabValues(Group.class, groupRed, groupBlack)
                .withPrefabValues(Voyage.class, voyageRed, voyageBlack)
                .suppress(Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(DeviceLightweight.class).verify();
    }

}