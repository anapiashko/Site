package com.anapiashko.site.controller;

import com.anapiashko.site.domain.Comment;
import com.anapiashko.site.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/comment")

public class CommentController {
    private final CommentRepo commentRepo;

    @Autowired
    CommentController(CommentRepo commentRepo){
        this.commentRepo = commentRepo;
    }

    @GetMapping
    public Iterable<Comment> getList() {
        return commentRepo.findAll();
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment){
        comment.setCreationDate(LocalDateTime.now());
        return commentRepo.save(comment);
    }
}
