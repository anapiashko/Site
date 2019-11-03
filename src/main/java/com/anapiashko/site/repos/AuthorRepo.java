package com.anapiashko.site.repos;

import com.anapiashko.site.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, String> {
}
