package com.example.citronix.web.VM.field;


import com.example.citronix.web.VM.farm.FarmVm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponseVm {

    private Long id;
    private Double area;
    private FarmVm farm;
}
