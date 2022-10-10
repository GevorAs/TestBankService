package com.agr.bankservice.repository;

import com.agr.bankservice.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {

    @Query(nativeQuery = true, value = "with cardId as (select ca.account_id as acc_id\n" +
            "                from session ss\n" +
            "                         join card ca on ca.session_id = ss.id\n" +
            "                where ss.token = 'zfsdf')\n" +
            "select acc.*\n" +
            "from account acc\n" +
            "         join cardId cid on cid.acc_id = acc.id\n" +
            "where acc.id = cid.acc_id\n"
    )
    Optional<AccountEntity> getAccountBySessionToken(@Param("token") String token);
}
