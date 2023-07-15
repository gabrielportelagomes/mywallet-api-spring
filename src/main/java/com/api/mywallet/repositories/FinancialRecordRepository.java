package com.api.mywallet.repositories;

import com.api.mywallet.model.record.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, UUID> {
}
