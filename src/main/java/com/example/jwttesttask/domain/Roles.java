package com.example.jwttesttask.domain;

import javax.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
