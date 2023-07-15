package com.api.mywallet.controllers;

import com.api.mywallet.model.record.FinancialRecord;
import com.api.mywallet.model.record.FinancialRecordDTO;
import com.api.mywallet.services.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("api/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService service;

    @GetMapping
    public ResponseEntity<List<FinancialRecord>> findFinancialRecordS(Authentication authentication) {
        List<FinancialRecord> records = service.findFinancialRecords(authentication);

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createFinancialRecord(@RequestBody @Valid FinancialRecordDTO data, Authentication authentication) {
        service.createFinancialRecord(data, authentication);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFinancialRecord(@PathVariable UUID id, Authentication authentication) {
        service.deleteFinancialRecord(id, authentication);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
