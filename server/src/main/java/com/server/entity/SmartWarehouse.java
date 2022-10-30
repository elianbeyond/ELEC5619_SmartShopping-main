package com.server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="smart_warehouse",schema = "backend")
@Data
public class SmartWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer id;

    private Integer price;

    private String name;

    private String headquarterLocation;
}
