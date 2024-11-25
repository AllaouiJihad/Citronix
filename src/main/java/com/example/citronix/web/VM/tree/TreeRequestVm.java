package com.example.citronix.web.VM.tree;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeRequestVm {
    @NotNull(message = "Planting date is required.")
    private LocalDate plantingDate;

}
