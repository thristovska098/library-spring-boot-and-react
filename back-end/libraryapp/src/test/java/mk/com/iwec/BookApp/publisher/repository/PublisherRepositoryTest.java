package mk.com.iwec.BookApp.publisher.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PublisherRepositoryTest {

	@Autowired
	PublisherRepository repo;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(repo).isNotNull();
	}

	@Test
	public void findPublisherWithIdThatDontExist() {
		Optional<Publisher> optionalMock = repo.findById((long) -100);
		assertThat(optionalMock).isNotPresent();

	}

	@Test
	public void savePublisherTest() {
		Publisher mock = TestUtil.createMockPublisher();
		repo.save(mock);

		Optional<Publisher> persistedOptionalPublisher = repo.findById(mock.getPublisherId());
		assertThat(persistedOptionalPublisher).isPresent();

		Publisher persistedPublisher = persistedOptionalPublisher.get();
		assertThat(persistedPublisher.getAddress()).isEqualTo(mock.getAddress());
		assertThat(persistedPublisher.getName()).isEqualTo(mock.getName());
		assertThat(persistedPublisher.getPublisherId()).isEqualTo(mock.getPublisherId());
		assertThat(persistedPublisher.getBooks()).isEqualTo(mock.getBooks());
	}

	@Test
	public void getAllPublishersTest() {
		Publisher mock = TestUtil.createMockPublisher();
		repo.save(mock);

		List<Publisher> publishers = repo.findAll();
		assertThat(publishers).isNotEmpty();
	}

	@Test
	public void updatePublisherTest() {
		// Create and validate publisher
		Publisher mock = TestUtil.createMockPublisher();
		repo.save(mock);

		Optional<Publisher> persistedOptionalPublisher = repo.findById(mock.getPublisherId());
		assertThat(persistedOptionalPublisher).isPresent();

		Publisher persistedPublisher = persistedOptionalPublisher.get();
		assertThat(persistedPublisher.getAddress()).isEqualTo(mock.getAddress());
		assertThat(persistedPublisher.getName()).isEqualTo(mock.getName());
		assertThat(persistedPublisher.getPublisherId()).isEqualTo(mock.getPublisherId());
		assertThat(persistedPublisher.getBooks()).isEqualTo(mock.getBooks());

		// Update Publisher
		Publisher mockForUpdate = persistedPublisher;
		mockForUpdate.setAddress("Updated Address");
		mockForUpdate.setBooks(null);
		mockForUpdate.setName("Updated Name");

		repo.saveAndFlush(mockForUpdate);

		Optional<Publisher> updatedOptionalMock = repo.findById(mockForUpdate.getPublisherId());
		assertThat(updatedOptionalMock).isPresent();
		Publisher updatedMock = updatedOptionalMock.get();
		assertThat(updatedMock.getAddress()).isEqualTo(mockForUpdate.getAddress());
		assertThat(updatedMock.getName()).isEqualTo(mockForUpdate.getName());
		assertThat(updatedMock.getPublisherId()).isEqualTo(mockForUpdate.getPublisherId());
		assertThat(updatedMock.getBooks()).isEqualTo(mockForUpdate.getBooks());
	}

	@Test
	public void deletePublisherTest() {
		// Create Publisher and verify
		Publisher mock = TestUtil.createMockPublisher();
		repo.save(mock);

		Optional<Publisher> persistedOptionalPublisher = repo.findById(mock.getPublisherId());
		assertThat(persistedOptionalPublisher).isPresent();

		Publisher persistedPublisher = persistedOptionalPublisher.get();
		assertThat(persistedPublisher.getAddress()).isEqualTo(mock.getAddress());
		assertThat(persistedPublisher.getName()).isEqualTo(mock.getName());
		assertThat(persistedPublisher.getPublisherId()).isEqualTo(mock.getPublisherId());
		assertThat(persistedPublisher.getBooks()).isEqualTo(mock.getBooks());

		// delete Publisher
		repo.deleteById(mock.getPublisherId());
		Optional<Publisher> optionalMock = repo.findById(mock.getPublisherId());
		assertThat(optionalMock).isNotPresent();
	}

}