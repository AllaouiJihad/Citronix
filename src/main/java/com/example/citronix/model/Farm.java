package com.example.citronix.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Double area;

    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields = new ArrayList<>();


}
