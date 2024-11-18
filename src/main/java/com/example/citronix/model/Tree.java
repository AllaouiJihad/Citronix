package com.example.citronix.model;

import com.example.citronix.model.enums.TreeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Field field;

    @Enumerated(EnumType.STRING)
    private TreeStatus status; // État de l'arbre

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    /*public int calculateAge() {
        return Period.between(this.plantingDate, LocalDate.now()).getYears();
    }*/

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", plantingDate=" + plantingDate +
                ", field=" + field +
                ", status=" + status +
                ", harvestDetails=" + harvestDetails +
                '}';
    }
}
