package com.example.jwttesttask.domain;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "item_code")
    private Long itemCode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "status")
    private String status;


    public Products() {

    }
}
