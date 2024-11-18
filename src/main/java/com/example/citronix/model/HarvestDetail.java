package com.example.citronix.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Harvest harvest;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tree tree;

    @Column(nullable = false)
    private Double quantity; // En kg

    @Override
    public String toString() {
        return "HarvestDetail{" +
                "id=" + id +
                ", harvest=" + harvest +
                ", tree=" + tree +
                ", quantity=" + quantity +
                '}';
    }
}
