package com.microgis.service;

import com.microgis.persistence.dto.DeliveryStatus;
import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.entity.TruckDelivery;
import com.microgis.persistence.repository.AccountRepository;
import com.microgis.persistence.repository.TruckDeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TruckDeliveryServiceTest {

    @InjectMocks
    private TruckDeliveryService truckDeliveryService;

    @Mock
    private TruckDeliveryRepository truckDeliveryRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private DeviceService deviceService;

    @Mock
    private DriverService driverService;

    @Test
    void testSaveOrUpdateDeliveryInformation() {
        //given
        var truckDeliveryInfo = TruckServiceTestFixtures.createTruckDeliveryInfo();
        when(truckDeliveryRepository.findTruckDeliveryByLoadNumber(truckDeliveryInfo.getLoadNumber()))
                .thenReturn(Optional.empty());
        when(deviceService.findDeviceLightweightByLicensePlate(truckDeliveryInfo.getTruck().getPlateNumber()))
                .thenReturn(null);
        when(driverService.findDriverByDisplayNameAndContactPhone(truckDeliveryInfo.getDriver().getName(), truckDeliveryInfo.getDriver().getPhone()))
                .thenReturn(null);
        when(accountRepository.getOne(1)).thenReturn(TruckServiceTestFixtures.createAccountLightweight());

        //when
        truckDeliveryService.saveOrUpdateDeliveryInformation(truckDeliveryInfo);

        //then
        verify(truckDeliveryRepository, times(1)).save(any(TruckDelivery.class));
        verify(driverService, times(1)).save(any(Driver.class));
        verify(deviceService, times(1)).save(any(DeviceLightweight.class));
    }

    @Test
    void testGetDeliveryResponse() {
        //given
        var truckDelivery = TruckServiceTestFixtures.createTruckDelivery();
        when(truckDeliveryRepository.findTruckDeliveryByLoginAndStatus("username", DeliveryStatus.CREATED))
                .thenReturn(Optional.of(truckDelivery));

        //when
        var deliveryResponse = truckDeliveryService.getDeliveryResponse("username");

        //then
        assertEquals("bricks", deliveryResponse.getCargoType());
        assertEquals("name", deliveryResponse.getDriverName());
        assertEquals("1051 AMBOY AVE", deliveryResponse.getAddressLineFrom().getAddress());
        assertEquals("203 WEST AVE", deliveryResponse.getAddressLineTo().getAddress());
        assertEquals(1, deliveryResponse.getDeviceId());
        assertEquals(344, deliveryResponse.getWeight());
    }

    @Test
    void testGetDeliveryResponseNull() {
        //given
        when(truckDeliveryRepository.findTruckDeliveryByLoginAndStatus("username", DeliveryStatus.CREATED))
                .thenReturn(Optional.empty());

        //when
        var deliveryResponse = truckDeliveryService.getDeliveryResponse("username");

        //then
        assertNull(deliveryResponse);
    }

}