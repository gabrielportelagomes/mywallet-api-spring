package com.api.mywallet.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFinancialRecordDTO(Integer value, String description) {
}
