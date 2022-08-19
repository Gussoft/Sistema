package com.gussoft.shoppingservice.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private Long id;

    private String name;
    private String description;
    private Double price;

    private Integer stock;
    private String status;

    private Date createAt;

    private Category category;

}
