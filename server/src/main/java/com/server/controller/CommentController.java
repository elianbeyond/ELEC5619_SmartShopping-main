package com.server.controller;


import com.server.common.R;
import com.server.entity.Comment;
import com.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getCommentList/{productId}/{currentPage}/{pageSize}")
    public R getCommentList(@PathVariable("productId") Integer productId,
                            @PathVariable("currentPage") Integer currentPage,
                            @PathVariable("pageSize") Integer pageSize) {
        return R.success(commentService.getCommentList(productId, currentPage, pageSize));
    }

    @PostMapping("/saveComment")
    public R saveComment(@RequestBody Comment comment) {
        return R.success(commentService.saveComment(comment));
    }

}
