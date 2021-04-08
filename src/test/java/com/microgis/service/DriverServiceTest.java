package com.microgis.service;

import com.microgis.persistence.entity.Driver;
import com.microgis.persistence.repository.DriverRepository;
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
class DriverServiceTest {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;


    @Test
    void testFindDriverByDisplayNameAndContactPhoneEmpty() {
        //given
        when(driverRepository.findDriverByDisplayNameAndContactPhone("driverName", "driverPhoneNumber"))
                .thenReturn(Optional.empty());

        //when
        var driver = driverService.findDriverByDisplayNameAndContactPhone("driverName", "driverPhoneNumber");

        //then
        assertNull(driver);
    }

    @Test
    void testFindDriverByDisplayNameAndContactPhone() {
        //given
        when(driverRepository.findDriverByDisplayNameAndContactPhone("driverName", "driverPhoneNumber"))
                .thenReturn(Optional.of(TruckServiceTestFixtures.createDriver()));

        //when
        var driver = driverService.findDriverByDisplayNameAndContactPhone("driverName", "driverPhoneNumber");

        //then
        assertEquals(1, driver.getAccount().getId());
        assertEquals(1, driver.getId());
    }

    @Test
    void testSave() {
        //when
        driverService.save(TruckServiceTestFixtures.createDriver());

        //then
        verify(driverRepository, times(1)).save(any(Driver.class));
    }

}