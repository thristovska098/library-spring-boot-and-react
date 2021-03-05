package mk.com.iwec.BookApp.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.com.iwec.BookApp.category.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}