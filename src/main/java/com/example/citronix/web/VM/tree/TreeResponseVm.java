package com.example.citronix.web.VM.tree;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeResponseVm {

    private  Long id;
    private LocalDate plantingDate;


}
