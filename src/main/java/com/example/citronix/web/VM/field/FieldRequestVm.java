package com.example.citronix.web.VM.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldRequestVm {
    @NotNull(message = "The area is required.")
    @Min(value = 1000, message = "The area must be at least 1000 m².")
    private Double area;
    @NotNull(message = "The farm ID is required.")
    private Long farmId;
}
