package com.microgis.persistence.repository;

import com.microgis.persistence.dto.DeliveryStatus;
import com.microgis.persistence.entity.TruckDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TruckDeliveryRepository extends JpaRepository<TruckDelivery, Integer> {

    @Query("select delivery from TruckDelivery delivery where delivery.driver.login = :login and delivery.status = :status")
    Optional<TruckDelivery> findTruckDeliveryByUserNameAndStatus(@Param("login") String login, @Param("status") DeliveryStatus status);

    @Query("select delivery from TruckDelivery delivery where delivery.loadNumber = :loadNumber")
    Optional<TruckDelivery> findTruckDeliveryByLoadNumber(@Param("loadNumber") String loadNumber);

}