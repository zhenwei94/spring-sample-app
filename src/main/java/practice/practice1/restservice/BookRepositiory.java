package practice.practice1.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositiory extends JpaRepository<Book, Long> {
}
