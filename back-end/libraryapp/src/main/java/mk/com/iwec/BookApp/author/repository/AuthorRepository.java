package mk.com.iwec.BookApp.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.com.iwec.BookApp.author.domain.*;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}