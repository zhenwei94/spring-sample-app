package com.example.restfulDemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositiory extends JpaRepository<Book, Long> {
}
