package com.test.repository;

import com.test.entity.Balance;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Balance e where e.id = ?1")
    Optional<Balance> findByIdForUpdate(UUID id);
}
