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

    @ManyToOne(fetch = FetchType.LAZY)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tree tree;

    private Double quantity;


}
