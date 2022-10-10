package com.agr.bankservice.repository;

import com.agr.bankservice.model.CardEntity;
import com.agr.bankservice.model.enums.CardStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepo extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findByCardNumberAndState(String cardNumber, CardStateEnum state);

    Optional<CardEntity> findByCardNumber(String cardNumber);
}
