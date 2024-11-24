package com.example.citronix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Farm farm;

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tree> trees = new ArrayList<>();


}
