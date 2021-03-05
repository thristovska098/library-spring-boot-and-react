package mk.com.iwec.BookApp.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.com.iwec.BookApp.book.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findByName(String name);
}
