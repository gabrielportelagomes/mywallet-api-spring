package com.api.mywallet.repositories;

import com.api.mywallet.model.record.FinancialRecord;
import com.api.mywallet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, UUID> {

    @Query("SELECT r FROM FinancialRecord r WHERE r.user = :user AND MONTH(r.createdAt) = MONTH(:currentDate) AND YEAR(r.createdAt) = YEAR(:currentDate)")
    List<FinancialRecord> findRecordsByUserAndCurrentMonth(User user, LocalDateTime currentDate);

}
