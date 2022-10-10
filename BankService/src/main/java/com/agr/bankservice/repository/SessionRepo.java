package com.agr.bankservice.repository;

import com.agr.bankservice.model.SessionEntity;
import com.agr.bankservice.model.enums.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<SessionEntity, Long> {
    boolean existsByTokenAndState(String secret, SessionState sessionState);

    Optional<SessionEntity> findByTokenAndState(String secret, SessionState sessionState);

}
