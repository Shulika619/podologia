package dev.shulika.podologia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Price")
@Getter
@Setter
public class PriceFullInfo {
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

    @Column(name = "enabled")
    private Boolean enabled;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}