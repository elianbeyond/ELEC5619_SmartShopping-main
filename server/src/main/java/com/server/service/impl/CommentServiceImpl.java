package com.server.service.impl;

import com.server.entity.Comment;
import com.server.entity.Product;
import com.server.entity.vo.CommentVO;
import com.server.mapper.CommentMapper;
import com.server.mapper.ProductMapper;
import com.server.mapper.UserMapper;
import com.server.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<CommentVO> getCommentList(Integer productId, Integer currentPage, Integer pageSize) {
        List<CommentVO> resultList = new ArrayList<>();
        List<Comment> commentList = commentMapper.getCommentList(productId, (currentPage - 1) * pageSize, pageSize);
        for (Comment comment : commentList) {
            CommentVO commentVO = this.accept(comment);
            resultList.add(commentVO);
        }
        return resultList;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentMapper.save(comment);
    }

    private CommentVO accept(Comment comment) {
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        Integer productId = comment.getProductId();
        Product product = productMapper.getProductById(productId);
        commentVO.setProductName(product.getName());
        Integer userId = comment.getUserId();
        userMapper.findById(userId).ifPresent(user -> commentVO.setPublicUserName(user.getUsername()));
        return commentVO;
    }
}
