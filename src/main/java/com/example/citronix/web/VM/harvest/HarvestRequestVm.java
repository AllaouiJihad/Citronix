package com.example.citronix.web.VM.harvest;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HarvestRequestVm {
    @NotNull(message = "Date is required")
    private LocalDate harvestDate;
}
