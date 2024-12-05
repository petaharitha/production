package com.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supplier.entity.Comment;
import com.supplier.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/vendors/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/quotes/{quoteId}")
    public ResponseEntity<Comment> addCommentToQuote(@RequestHeader ("Authorization") String jwt,@PathVariable Long quoteId, @RequestBody Comment comment) {
        Comment createdComment = commentService.addCommentToQuote(quoteId, comment);
        return ResponseEntity.ok(createdComment);
    }

    @PostMapping("/rfqs/{rfqId}")
    public ResponseEntity<Comment> addCommentToRfq(@RequestHeader ("Authorization") String jwt,@PathVariable Long rfqId, @RequestBody Comment comment) {
        Comment createdComment = commentService.addCommentToRfq(rfqId, comment);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/quotes/{quoteId}")
    public ResponseEntity<List<Comment>> getCommentsForQuote(@RequestHeader ("Authorization") String jwt,@PathVariable Long quoteId) {
        List<Comment> comments = commentService.getCommentsForQuote(quoteId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/rfqs/{rfqId}")
    public ResponseEntity<List<Comment>> getCommentsForRfq(@RequestHeader ("Authorization") String jwt,@PathVariable Long rfqId) {
        List<Comment> comments = commentService.getCommentsForRfq(rfqId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@RequestHeader ("Authorization") String jwt,@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}


