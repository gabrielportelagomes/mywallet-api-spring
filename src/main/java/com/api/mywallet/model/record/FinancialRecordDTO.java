package com.api.mywallet.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FinancialRecordDTO(
        @NotNull(message = "Value is required")
        Integer value,
        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Category is required")
        Category category) {
}
