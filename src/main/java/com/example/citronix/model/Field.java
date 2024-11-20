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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Farm farm;

    @OneToMany(mappedBy = "field")
    private List<Tree> trees = new ArrayList<>();

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", area=" + area +
                ", farm=" + farm +
                ", trees=" + trees +
                '}';
    }
}
