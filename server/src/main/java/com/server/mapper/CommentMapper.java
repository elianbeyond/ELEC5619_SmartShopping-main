package com.server.mapper;

import com.server.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//spring-data-jpa
//select * from user where name=?


public interface CommentMapper extends CrudRepository<Comment, Integer> {

    @Query(nativeQuery = true, value = "select * from comment where product_id = ?1 order by rate desc limit ?2 , ?3")
    List<Comment> getCommentList(Integer productId, Integer pageStart, Integer pageSize);

}
