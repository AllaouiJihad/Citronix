package com.example.citronix.web.VM.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SaleRequestVm {
    @NotNull(message = "date is required")
    private LocalDate saleDate;

    @NotNull(message = "unitPrice is required")
    @Positive(message = "unitPrice should be positive")
    private double unitPrice;

    @NotBlank(message = "client name is required")
    private String client;
    @NotBlank(message = "quantity is required")
    private double quantity;

}
