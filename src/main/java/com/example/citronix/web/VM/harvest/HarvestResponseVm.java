package com.example.citronix.web.VM.harvest;

import com.example.citronix.model.enums.Season;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HarvestResponseVm {
    private Long id;
    private Season season;
    private LocalDate date;
    private double totalQuantity;
}
