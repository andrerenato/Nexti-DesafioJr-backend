package com.nexti.desafio.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String sku;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double value;

    private Integer quantity;

}
