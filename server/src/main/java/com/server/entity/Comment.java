package com.server.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="comment",schema = "backend")

// jpa needs an id for mapping entities, use the joint primary key
@IdClass(Comment.class)

public class Comment implements Serializable {

    @Id
    private Integer productId;

    @Id
    private Integer userId;

    private Integer rate;

    private String content;
}
