package com.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="order",schema = "backend")
//jpa needs an id for mapping entities, use the joint primary key
@IdClass(Order.class)
public class Order implements Serializable {
    @Id
    private Integer productId;
    @Id
    private Integer userId;

    private Integer warehouse_id;

    private Integer quantity;


}
