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

    @OneToMany(mappedBy = "farm",fetch = FetchType.LAZY)
    private List<Field> fields = new ArrayList<>();

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", area=" + area +
                ", creationDate=" + creationDate +
                ", fields=" + fields.size() +
                '}';
    }
}
