package mk.com.iwec.BookApp.category.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository repo;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(repo).isNotNull();
	}

	@Test
	public void findCategoryWithIdThatDontExist() {
		Optional<Category> mock = repo.findById((long) -100);
		assertThat(mock).isNotPresent();

	}

	@Test
	public void saveCategoryTest() {
		Category mock = TestUtil.createMockCategory();
		repo.save(mock);

		Optional<Category> persistedOptional = repo.findById(mock.getCategoryId());
		assertThat(persistedOptional).isPresent();

		Category persistedCategory = persistedOptional.get();
		assertThat(persistedCategory.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persistedCategory.getCategoryId()).isEqualTo(mock.getCategoryId());
		assertThat(persistedCategory.getName()).isEqualTo(mock.getName());
	}

	@Test
	public void getAllCategoriesTest() {
		Category mock = TestUtil.createMockCategory();
		repo.save(mock);

		List<Category> categories = repo.findAll();
		assertThat(categories).isNotEmpty();
	}

	@Test
	public void updateCategoryTest() {
		// Create and validate Category
		Category mock = TestUtil.createMockCategory();
		repo.save(mock);
		Optional<Category> persistedOptional = repo.findById(mock.getCategoryId());
		assertThat(persistedOptional).isPresent();
		Category persistedCategory = persistedOptional.get();
		assertThat(persistedCategory.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persistedCategory.getCategoryId()).isEqualTo(mock.getCategoryId());
		assertThat(persistedCategory.getName()).isEqualTo(mock.getName());

		// Update Category
		Category categoryForUpdate = persistedCategory;
		categoryForUpdate.setBooks(null);
		categoryForUpdate.setName("Updated name");

		repo.saveAndFlush(categoryForUpdate);

		Optional<Category> updatedOptional = repo.findById(categoryForUpdate.getCategoryId());
		assertThat(updatedOptional).isPresent();
		Category updated = updatedOptional.get();
		assertThat(updated.getBooks()).isEqualTo(categoryForUpdate.getBooks());
		assertThat(updated.getCategoryId()).isEqualTo(categoryForUpdate.getCategoryId());
		assertThat(updated.getName()).isEqualTo(categoryForUpdate.getName());
	}

	@Test
	public void deleteCategoryTest() {
		// Create Category and verify
		Category mock = TestUtil.createMockCategory();
		repo.save(mock);
		Optional<Category> persistedOptional = repo.findById(mock.getCategoryId());
		assertThat(persistedOptional).isPresent();
		Category persistedCategory = persistedOptional.get();
		assertThat(persistedCategory.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persistedCategory.getCategoryId()).isEqualTo(mock.getCategoryId());
		assertThat(persistedCategory.getName()).isEqualTo(mock.getName());

		// delete Category
		repo.deleteById(mock.getCategoryId());
		Optional<Category> deletedCategory = repo.findById(mock.getCategoryId());
		assertThat(deletedCategory).isNotPresent();
	}

}
