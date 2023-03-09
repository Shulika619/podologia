package dev.shulika.podologia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Procedure")
@Setter
@Getter
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "podolog_expert_minutes")
    private Integer podologExpertMinutes;

    @Column(name = "podolog_expert_price")
    private Integer podologExpertPrice;

    @Column(name = "podolog_minutes")
    private Integer podologMinutes;

    @Column(name = "podolog_price")
    private Integer podologPrice;

    @Column(name = "enabled")
    private Boolean enabled;

}