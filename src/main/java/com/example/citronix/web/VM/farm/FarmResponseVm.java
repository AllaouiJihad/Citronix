package com.example.citronix.web.VM.farm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmResponseVm {
    private Long id;
    private String name;
    private String location;
    private Double area;
    private String creationDate;
}
