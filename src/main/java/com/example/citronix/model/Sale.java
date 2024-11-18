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

    private Double unitPrice; // Prix par kg

    private Double quantity; // Quantité vendue en kg

    private String client;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Harvest harvest;

   /* public Double calculateRevenue() {
        return this.quantity * this.unitPrice;
    }*/

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", client='" + client + '\'' +
                ", harvest=" + harvest +
                '}';
    }
}
