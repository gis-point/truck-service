package com.microgis.persistence.repository;

import com.microgis.persistence.entity.AccountLightweight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountLightweight, Integer> {

}