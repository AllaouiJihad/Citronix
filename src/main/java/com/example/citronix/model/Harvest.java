package com.example.citronix.model;

import com.example.citronix.model.enums.Season;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season; // Saison de récolte

    @Column(nullable = false)
    private LocalDate harvestDate;

    @Column(nullable = false)
    private Double totalQuantity; // En kg

    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails = new ArrayList<>();

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales = new ArrayList<>();

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + id +
                ", season=" + season +
                ", harvestDate=" + harvestDate +
                ", totalQuantity=" + totalQuantity +
                ", harvestDetails=" + harvestDetails +
                ", sales=" + sales +
                '}';
    }
}
