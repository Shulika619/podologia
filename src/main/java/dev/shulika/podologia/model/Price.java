package dev.shulika.podologia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Price")
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    @OneToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "price")
    private Integer price;

}