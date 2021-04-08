package com.microgis.service;

import com.microgis.persistence.entity.DeviceLightweight;
import com.microgis.persistence.repository.DeviceRepository;
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
class DeviceServiceTest {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Test
    void testFindByIdEmpty() {
        //given
        when(deviceRepository.findById(1)).thenReturn(Optional.empty());

        //when
        var deviceLightweight = deviceService.findById(1);

        //then
        assertNull(deviceLightweight);
    }

    @Test
    void testFindById() {
        //given
        when(deviceRepository.findById(1)).thenReturn(Optional.of(TruckServiceTestFixtures.createDeviceLightweight()));

        //when
        var deviceLightweight = deviceService.findById(1);

        //then
        assertEquals(1, deviceLightweight.getAccount().getId());
        assertEquals(1, deviceLightweight.getId());
    }

    @Test
    void testFindDeviceLightweightByLicensePlateEmpty() {
        //given
        when(deviceRepository.findDeviceLightweightByLicensePlate("licensePlate")).thenReturn(Optional.empty());

        //when
        var deviceLightweight = deviceService.findDeviceLightweightByLicensePlate("licensePlate");

        //then
        assertNull(deviceLightweight);
    }

    @Test
    void testFindDeviceLightweightByLicensePlate() {
        //given
        when(deviceRepository.findDeviceLightweightByLicensePlate("licensePlate"))
                .thenReturn(Optional.of(TruckServiceTestFixtures.createDeviceLightweight()));

        //when
        var deviceLightweight = deviceService.findDeviceLightweightByLicensePlate("licensePlate");

        //then
        assertEquals(1, deviceLightweight.getAccount().getId());
        assertEquals(1, deviceLightweight.getId());
    }

    @Test
    void testSave() {
        //when
        deviceService.save(TruckServiceTestFixtures.createDeviceLightweight());

        //then
        verify(deviceRepository, times(1)).save(any(DeviceLightweight.class));
    }

}