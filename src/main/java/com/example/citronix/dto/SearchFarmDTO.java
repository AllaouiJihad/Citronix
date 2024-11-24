package com.example.citronix.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFarmDTO {
    private String name;
    private String location;
}
