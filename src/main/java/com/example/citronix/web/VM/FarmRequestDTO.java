package com.example.citronix.web.VM;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class FarmRequestDTO {
    @NotBlank(message = "Le nom de la ferme est obligatoire")
    private String name;

    @NotBlank(message = "La localisation de la ferme est obligatoire")
    private String location;

    @NotNull(message = "La superficie est obligatoire")
    @Positive(message = "La superficie doit être un nombre positif")
    private Double area;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDate creationDate;


}
