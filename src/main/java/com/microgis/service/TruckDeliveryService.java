package com.microgis.service;

import com.microgis.controller.dto.TruckDeliveryInfo;
import com.microgis.persistence.TruckDeliveryRepository;
import com.microgis.persistence.entity.TruckDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TruckDeliveryService {

    private final TruckDeliveryRepository truckDeliveryRepository;

    public void saveDeliveryInformation(TruckDeliveryInfo truckDeliveryInfo) {
        TruckDelivery truckDelivery = new TruckDelivery();
        truckDelivery.setLoadNumber(truckDeliveryInfo.getLoadNumber());
        truckDelivery.setTrailerNumber(truckDeliveryInfo.getTrailerNumber());
        truckDelivery.setAddressLineFrom(truckDeliveryInfo.getAddressLineFrom());
        truckDelivery.setCityFrom(truckDeliveryInfo.getCityFrom());
        truckDelivery.setStateFrom(truckDeliveryInfo.getStateFrom());
        truckDelivery.setTimeFrom(truckDeliveryInfo.getTimeFrom());
        truckDelivery.setAddressLineTo(truckDeliveryInfo.getAddressLineTo());
        truckDelivery.setCityTo(truckDeliveryInfo.getCityTo());
        truckDelivery.setStateTo(truckDeliveryInfo.getStateTo());
        truckDelivery.setTimeTo(truckDeliveryInfo.getTimeTo());
        truckDelivery.setStatus(truckDeliveryInfo.getStatus());
        truckDeliveryRepository.save(truckDelivery);
    }

}