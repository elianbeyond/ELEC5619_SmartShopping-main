package com.server.service;

import com.server.entity.Comment;
import com.server.entity.vo.CommentVO;

import java.util.List;

public interface CommentService {

    List<CommentVO>  getCommentList(Integer productId, Integer currentPage, Integer pageSize);

    Comment saveComment(Comment comment);

}
