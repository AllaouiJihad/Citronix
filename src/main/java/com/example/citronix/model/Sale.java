package com.example.citronix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate saleDate;

    private Double unitPrice;

    private Double quantity;

    private String client;


    @ManyToOne(fetch = FetchType.LAZY)
    private Harvest harvest;

   /* public Double calculateRevenue() {
        return this.quantity * this.unitPrice;
    }*/

}
