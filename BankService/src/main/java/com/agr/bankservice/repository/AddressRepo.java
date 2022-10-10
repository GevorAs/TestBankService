package com.agr.bankservice.repository;

import com.agr.bankservice.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressEntity, Long> {
}
