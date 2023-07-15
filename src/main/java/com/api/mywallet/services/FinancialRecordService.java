package com.api.mywallet.services;

import com.api.mywallet.exception.NotFoundException;
import com.api.mywallet.model.record.FinancialRecord;
import com.api.mywallet.model.record.FinancialRecordDTO;
import com.api.mywallet.model.user.User;
import com.api.mywallet.repositories.FinancialRecordRepository;
import com.api.mywallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class FinancialRecordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FinancialRecordRepository financialRecordRepository;

    public void createFinancialRecord(FinancialRecordDTO data, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email);

        if(user == null) {
            throw new NotFoundException("User not found");
        }

        FinancialRecord financialRecord = new FinancialRecord(data.value(), data.description(), data.category(), user);
        financialRecordRepository.save(financialRecord);
    }
}
