package com.example.citronix.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Field field;


    @OneToMany(mappedBy = "tree",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    public int calculateAge() {

        return Period.between(this.plantingDate, LocalDate.now()).getYears();
    }

    public String treeStatus(){
        int age = calculateAge();
        if (age < 3) {
            return "YOUNG";
        } else if (age <= 10) {
            return "MATURE";
        } else if (age <= 20) {
            return "OLD";
        } else {
            return "NON_PRODUCTIVE";
        }
    }

    public double treeProductivity(){
        int age = calculateAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12;
        } else if (age <= 20) {
            return 20;
        } else {
            return 0;
        }
    }

}
