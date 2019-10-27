package com.anapiashko.site.repos;

import com.anapiashko.site.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
}
