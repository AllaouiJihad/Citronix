package com.example.citronix.web.VM.field;

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
    @NotNull(message = "La superficie est obligatoire")
    @Positive(message = "La superficie doit être un nombre positif")
    private Double area;
    @NotNull(message = "L'id de ferme est obligatoire")
    private Long farmId;
}
