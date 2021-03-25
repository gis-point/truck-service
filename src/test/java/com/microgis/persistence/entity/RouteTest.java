package com.microgis.persistence.entity;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class RouteTest {

    @Test
    void testEquals() {
        AccountLightweight accountLightweightRed = new AccountLightweight();
        accountLightweightRed.setAddressGeocodingServer("accountLightweightRed");
        AccountLightweight accountLightweightBlack = new AccountLightweight();
        accountLightweightBlack.setAddressGeocodingServer("accountLightweightBlack");
        GeoZoneLightweight geoZoneLightweightRed = new GeoZoneLightweight();
        geoZoneLightweightRed.setAddress("geoZoneLightweightRed");
        GeoZoneLightweight geoZoneLightweightBlack = new GeoZoneLightweight();
        geoZoneLightweightBlack.setAddress("geoZoneLightweightBlack");
        Group groupRed = new Group();
        groupRed.setCode("groupRed");
        Group groupBlack = new Group();
        groupBlack.setCode("groupBlack");
        Route routeRed = new Route();
        routeRed.setActualWaypoints("routeRed");
        Route routeBlack = new Route();
        routeBlack.setActualWaypoints("routeBlack");
        Schedule scheduleRed = new Schedule();
        scheduleRed.setDescription("scheduleRed");
        Schedule scheduleBlack = new Schedule();
        scheduleBlack.setDescription("scheduleBlack");
        RoutePoint routePointRed = new RouteDevice();
        RoutePoint routePointBlack = new RouteDevice();
        routePointBlack.setRoute(routeBlack);
        EqualsVerifier.forClass(Route.class)
                .withPrefabValues(AccountLightweight.class, accountLightweightRed, accountLightweightBlack)
                .withPrefabValues(Group.class, groupRed, groupBlack)
                .withPrefabValues(Route.class, routeRed, routeBlack)
                .withPrefabValues(GeoZoneLightweight.class, geoZoneLightweightRed, geoZoneLightweightBlack)
                .withPrefabValues(Schedule.class, scheduleRed, scheduleBlack)
                .withPrefabValues(RoutePoint.class, routePointRed, routePointBlack)
                .suppress(Warning.ALL_NONFINAL_FIELDS_SHOULD_BE_USED)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(Route.class).verify();
    }

}