package mk.com.iwec.BookApp.book.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BookRepositoryTest {

	@Autowired
	BookRepository repo;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(repo).isNotNull();
	}

	@Test
	public void findBookWithIdThatDontExist() {
		Optional<Book> mock = repo.findById((long) -1);
		assertThat(mock).isNotPresent();
	}

	@Test
	public void saveBookTest() {
		Book mock = TestUtil.createMockBook();
		repo.save(mock);

		Optional<Book> persistedOptional = repo.findById(mock.getBookId());
		assertThat(persistedOptional).isPresent();

		Book persisted = persistedOptional.get();
		assertThat(mock.getAuthors()).isEqualTo(persisted.getAuthors());
		assertThat(mock.getCategories()).isEqualTo(persisted.getCategories());
		assertThat(mock.getDescription()).isEqualTo(persisted.getDescription());
		assertThat(mock.getEdition()).isEqualTo(persisted.getEdition());
		assertThat(mock.getName()).isEqualTo(persisted.getName());
		assertThat(mock.getPublisher()).isEqualTo(persisted.getPublisher());
		assertThat(mock.getSources()).isEqualTo(persisted.getSources());

	}

	@Test
	public void updateBookTest() {
		// Create and validate Book
		Book mock = TestUtil.createMockBook();
		repo.save(mock);

		Optional<Book> persistedOptional = repo.findById(mock.getBookId());
		assertThat(persistedOptional).isPresent();

		Book persisted = persistedOptional.get();
		assertThat(mock.getAuthors()).isEqualTo(persisted.getAuthors());
		assertThat(mock.getCategories()).isEqualTo(persisted.getCategories());
		assertThat(mock.getDescription()).isEqualTo(persisted.getDescription());
		assertThat(mock.getEdition()).isEqualTo(persisted.getEdition());
		assertThat(mock.getName()).isEqualTo(persisted.getName());
		assertThat(mock.getPublisher()).isEqualTo(persisted.getPublisher());
		assertThat(mock.getSources()).isEqualTo(persisted.getSources());

		// Update book
		Book mockForUpdate = persisted;
		mockForUpdate.setAuthors(null);
		mockForUpdate.setCategories(null);
		mockForUpdate.setDescription("Description for updated book");
		mockForUpdate.setEdition((long) 10);
		mockForUpdate.setName("Updated Book");
		mockForUpdate.setPublisher(null);
		mockForUpdate.setSources(null);

		repo.saveAndFlush(mockForUpdate);

		Optional<Book> updatedOptional = repo.findById(mock.getBookId());
		assertThat(updatedOptional).isPresent();
		Book updated = updatedOptional.get();
		assertThat(updated.getAuthors()).isEqualTo(mockForUpdate.getAuthors());
		assertThat(updated.getCategories()).isEqualTo(mockForUpdate.getCategories());
		assertThat(updated.getDescription()).isEqualTo(mockForUpdate.getDescription());
		assertThat(updated.getEdition()).isEqualTo(mockForUpdate.getEdition());
		assertThat(updated.getName()).isEqualTo(mockForUpdate.getName());
		assertThat(updated.getPublisher()).isEqualTo(mockForUpdate.getPublisher());
		assertThat(updated.getSources()).isEqualTo(mockForUpdate.getSources());
	}

	@Test
	public void deleteBookTest() {
		Book mock = TestUtil.createMockBook();
		repo.save(mock);

		Optional<Book> persistedOptional = repo.findById(mock.getBookId());
		assertThat(persistedOptional).isPresent();

		Book persisted = persistedOptional.get();
		assertThat(mock.getAuthors()).isEqualTo(persisted.getAuthors());
		assertThat(mock.getCategories()).isEqualTo(persisted.getCategories());
		assertThat(mock.getDescription()).isEqualTo(persisted.getDescription());
		assertThat(mock.getEdition()).isEqualTo(persisted.getEdition());
		assertThat(mock.getName()).isEqualTo(persisted.getName());
		assertThat(mock.getPublisher()).isEqualTo(persisted.getPublisher());
		assertThat(mock.getSources()).isEqualTo(persisted.getSources());

		// Delete book
		repo.deleteById(mock.getBookId());
		Optional<Book> deleted = repo.findById(mock.getBookId());
		assertThat(deleted).isNotPresent();

	}

	@Test
	public void findByNameTest() {
		Book mock = TestUtil.createMockBook();
		repo.save(mock);

		Optional<List<Book>> persistedOptional = Optional.of(repo.findByName(mock.getName()));
		assertThat(persistedOptional).isPresent();

	}

}
