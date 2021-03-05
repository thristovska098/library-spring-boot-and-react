package mk.com.iwec.BookApp.source.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mk.com.iwec.BookApp.source.domain.Source;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class SourceRepositoryTest {

	@Autowired
	private SourceRepository sourceRepo;

	@Test
	public void inectedComponentsAreNotNull() {
		assertThat(sourceRepo).isNotNull();
	}

	@Test
	public void findSourceWithIdThatDontExist() {
		Optional<Source> persistedOptionalMock = sourceRepo.findById((long) -11);
		assertThat(persistedOptionalMock).isNotPresent();

	}

	@Test
	public void saveSourceTest() {
		Source mock = TestUtil.createMockSource();
		sourceRepo.save(mock);

		Optional<Source> persistedOptionalMock = sourceRepo.findById(mock.getSourceId());
		assertThat(persistedOptionalMock).isPresent();
		Source persistedMock = persistedOptionalMock.get();

		assertThat(persistedMock.getUrl()).isEqualTo(mock.getUrl());
		assertThat(persistedMock.getImgSrc()).isEqualTo(mock.getImgSrc());
		assertThat(persistedMock.getFormat()).isEqualTo(mock.getFormat());
		assertThat(persistedMock.getSourceId()).isEqualTo(mock.getSourceId());
	}

	@Test
	public void getAllSourcesTest() {
		Source mock = TestUtil.createMockSource();
		sourceRepo.save(mock);

		List<Source> sources = sourceRepo.findAll();
		assertThat(sources).isNotEmpty();
	}

	@Test
	public void updateSourceTest() {
		// create source and verify
		Source mock = TestUtil.createMockSource();
		sourceRepo.save(mock);

		Optional<Source> persistedOptionalMock = sourceRepo.findById(mock.getSourceId());
		assertThat(persistedOptionalMock).isPresent();
		Source persistedMock = persistedOptionalMock.get();

		assertThat(persistedMock.getUrl()).isEqualTo(mock.getUrl());
		assertThat(persistedMock.getImgSrc()).isEqualTo(mock.getImgSrc());
		assertThat(persistedMock.getFormat()).isEqualTo(mock.getFormat());
		assertThat(persistedMock.getSourceId()).isEqualTo(mock.getSourceId());
		// Update source and verify
		Source mockForUpdate = persistedMock;
		mockForUpdate.setImgSrc("Updated image source");
		mockForUpdate.setFormat("Updated source format");
		mockForUpdate.setUrl("Updated url");

		sourceRepo.saveAndFlush(mockForUpdate);

		Optional<Source> persistedUpdatedOptionalMock = sourceRepo.findById(mockForUpdate.getSourceId());
		assertThat(persistedUpdatedOptionalMock).isPresent();

		Source updatedSource = persistedUpdatedOptionalMock.get();

		assertThat(updatedSource.getFormat()).isEqualTo(mockForUpdate.getFormat());
		assertThat(updatedSource.getImgSrc()).isEqualTo(mockForUpdate.getImgSrc());
		assertThat(updatedSource.getUrl()).isEqualTo(mockForUpdate.getUrl());
		assertThat(updatedSource.getSourceId()).isEqualTo(mockForUpdate.getSourceId());
	}

	@Test
	public void deleteSourceTest() {
		// create source and verify
		Source mock = TestUtil.createMockSource();
		sourceRepo.save(mock);

		Optional<Source> persistedOptionalMock = sourceRepo.findById(mock.getSourceId());
		assertThat(persistedOptionalMock).isPresent();
		Source persistedMock = persistedOptionalMock.get();

		assertThat(persistedMock.getUrl()).isEqualTo(mock.getUrl());
		assertThat(persistedMock.getImgSrc()).isEqualTo(mock.getImgSrc());
		assertThat(persistedMock.getFormat()).isEqualTo(mock.getFormat());
		assertThat(persistedMock.getSourceId()).isEqualTo(mock.getSourceId());

		// delete source
		sourceRepo.deleteById(persistedMock.getSourceId());
		Optional<Source> deleteOptionalSource = sourceRepo.findById(mock.getSourceId());
		assertThat(deleteOptionalSource).isNotPresent();

	}

}
