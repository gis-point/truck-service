package com.microgis.persistence;

import com.microgis.persistence.entity.TruckDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckDeliveryRepository extends JpaRepository<TruckDelivery, Integer> {

}