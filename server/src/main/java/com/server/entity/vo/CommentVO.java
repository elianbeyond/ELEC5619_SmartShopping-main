package com.server.entity.vo;

import com.server.entity.Comment;
import lombok.Data;

@Data
public class CommentVO extends Comment {

    private String publicUserName;

    private String productName;
}