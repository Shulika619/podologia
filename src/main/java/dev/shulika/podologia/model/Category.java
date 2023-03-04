package dev.shulika.podologia.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

//    @Enumerated(EnumType.STRING)  //TODO: ENUM status in model
    @Column(name = "status")
    private String status;
//    status varchar(20) default 'ACTIVE'

}