package mk.com.iwec.BookApp.author.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mk.com.iwec.BookApp.utils.TestUtil;
import mk.com.iwec.BookApp.author.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AuthorRepositoryTest {

	@Autowired
	AuthorRepository repo;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(repo).isNotNull();
	}

	@Test
	public void findAuthorWithIdThatDontExist() {
		Optional<Author> mock = repo.findById("-------------");
		assertThat(mock).isNotPresent();
	}

	@Test
	public void saveAuthorTest() {
		Author mock = TestUtil.createMockAuthor();
		repo.save(mock);

		Optional<Author> persistedOptional = repo.findById(mock.getSsn());
		assertThat(persistedOptional).isPresent();
		Author persisted = persistedOptional.get();

		assertThat(persisted.getBirthDate()).isEqualTo(mock.getBirthDate());
		assertThat(persisted.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persisted.getLastName()).isEqualTo(mock.getLastName());
		assertThat(persisted.getName()).isEqualTo(mock.getName());
		assertThat(persisted.getSsn()).isEqualTo(mock.getSsn());
	}

	@Test
	public void getAllAuthorsTest() {
		Author mock = TestUtil.createMockAuthor();
		repo.save(mock);
		List<Author> authors = repo.findAll();
		assertThat(authors).isNotEmpty();
	}

	@Test
	public void updateAuthorTest() {
		// Create and validate Author
		Author mock = TestUtil.createMockAuthor();
		repo.save(mock);
		Optional<Author> persistedOptional = repo.findById(mock.getSsn());
		assertThat(persistedOptional).isPresent();
		Author persisted = persistedOptional.get();
		assertThat(persisted.getBirthDate()).isEqualTo(mock.getBirthDate());
		assertThat(persisted.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persisted.getLastName()).isEqualTo(mock.getLastName());
		assertThat(persisted.getName()).isEqualTo(mock.getName());
		assertThat(persisted.getSsn()).isEqualTo(mock.getSsn());

		// Update Author
		Author mockForUpdate = persisted;
		persisted.setBirthDate(new Date());
		persisted.setBooks(null);
		persisted.setLastName("Updated last name");
		persisted.setName("Updated name");

		repo.saveAndFlush(mockForUpdate);

		Optional<Author> updatedOptional = repo.findById(mockForUpdate.getSsn());
		assertThat(updatedOptional).isPresent();
		Author updated = updatedOptional.get();
		assertThat(updated.getBirthDate()).isEqualTo(mockForUpdate.getBirthDate());
		assertThat(updated.getBooks()).isEqualTo(mockForUpdate.getBooks());
		assertThat(updated.getLastName()).isEqualTo(mockForUpdate.getLastName());
		assertThat(updated.getName()).isEqualTo(mockForUpdate.getName());
	}

	@Test
	public void deleteCategoryTest() {
		// Create and validate Author
		Author mock = TestUtil.createMockAuthor();
		repo.save(mock);
		Optional<Author> persistedOptional = repo.findById(mock.getSsn());
		assertThat(persistedOptional).isPresent();
		Author persisted = persistedOptional.get();
		assertThat(persisted.getBirthDate()).isEqualTo(mock.getBirthDate());
		assertThat(persisted.getBooks()).isEqualTo(mock.getBooks());
		assertThat(persisted.getLastName()).isEqualTo(mock.getLastName());
		assertThat(persisted.getName()).isEqualTo(mock.getName());
		assertThat(persisted.getSsn()).isEqualTo(mock.getSsn());

		// Delete Author
		repo.deleteById(mock.getSsn());
		Optional<Author> deleted = repo.findById(mock.getSsn());
		assertThat(deleted).isNotPresent();

	}
}
