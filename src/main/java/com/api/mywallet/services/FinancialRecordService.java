package com.api.mywallet.services;

import com.api.mywallet.exception.ForbiddenException;
import com.api.mywallet.exception.NotFoundException;
import com.api.mywallet.model.record.FinancialRecord;
import com.api.mywallet.model.record.FinancialRecordDTO;
import com.api.mywallet.model.record.UpdateFinancialRecordDTO;
import com.api.mywallet.model.user.User;
import com.api.mywallet.repositories.FinancialRecordRepository;
import com.api.mywallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FinancialRecordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FinancialRecordRepository financialRecordRepository;

    public List<FinancialRecord> findFinancialRecords(Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        LocalDateTime currentDate = LocalDateTime.now();
        List<FinancialRecord> records = financialRecordRepository.findRecordsByUserAndCurrentMonth(user, currentDate);

        return records;
    }

    public void createFinancialRecord(FinancialRecordDTO data, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        FinancialRecord financialRecord = new FinancialRecord(data.value(), data.description(), data.category(), user);
        financialRecordRepository.save(financialRecord);
    }

    public void deleteFinancialRecord(UUID id, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        FinancialRecord financialRecord = financialRecordRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Record not found");
        });

        if (!financialRecord.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Forbidden");
        }


        financialRecordRepository.deleteById(id);
    }

    public void updateFinancialRecord(UUID id, UpdateFinancialRecordDTO data, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        FinancialRecord financialRecord = financialRecordRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Record not found");
        });

        if (!financialRecord.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Forbidden");
        }

        if (data.value() != null) {
            financialRecord.setValue(data.value());
        }
        if (data.description() != null) {
            financialRecord.setDescription(data.description());
        }

        financialRecordRepository.save(financialRecord);
    }
}
