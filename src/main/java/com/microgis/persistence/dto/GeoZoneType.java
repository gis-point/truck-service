package com.microgis.persistence.dto;

public enum GeoZoneType {

    POINT_RADIUS,
    BOUNDED_RECT,
    SWEPT_POINT_RADIUS,
    POLYGON,
    POI,
    TRAFFIC,
    GEO_FENCE;

    public String getI18nCode() {
        switch (this) {

            case POINT_RADIUS:
                return "i18n_pointRadius";
            case BOUNDED_RECT:
                return "i18n_boundedRectangle";
            case SWEPT_POINT_RADIUS:
                return "i18n_distCircle";
            case POLYGON:
                return "i18n_polygon";
            case POI:
                return "i18n_poi";
            case TRAFFIC:
                return "i18n_traffic_name";
            case GEO_FENCE:
            default:
                return "i18n_geofences";
        }

    }

}
