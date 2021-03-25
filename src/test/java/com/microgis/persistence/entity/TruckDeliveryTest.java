package com.microgis.persistence.entity;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class TruckDeliveryTest {

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
        Group groupRed = new Group();
        groupRed.setCode("groupRed");
        Group groupBlack = new Group();
        groupBlack.setCode("groupBlack");
        EqualsVerifier.forClass(TruckDelivery.class)
                .withPrefabValues(AccountLightweight.class, accountLightweightRed, accountLightweightBlack)
                .withPrefabValues(DeviceLightweight.class, deviceLightweightRed, deviceLightweightBlack)
                .withPrefabValues(Group.class, groupRed, groupBlack)
                .suppress(Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(TruckDelivery.class).verify();
    }

}