package com.microgis.persistence.repository;

import com.microgis.persistence.entity.TruckDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TruckDeliveryRepository extends JpaRepository<TruckDelivery, Integer> {

    Optional<TruckDelivery> findTruckDeliveryByLoadNumber(@Param("loadNumber")Integer loadNumber);

}