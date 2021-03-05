package mk.com.iwec.BookApp.book.controller;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.dto.BookDto2;
import mk.com.iwec.BookApp.book.service.BookService;
import mk.com.iwec.BookApp.infrastructure.Endpoints;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BookService bookService;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(mockMvc).isNotNull();
		assertThat(objectMapper).isNotNull();
	}

	@Test
	public void getAllBooksTest_succes() {
		List<BookDto2> mockDtoList = new ArrayList<>();
		BookDto2 mock1 = TestUtil.createMockBookDto2();
		BookDto2 mock2 = TestUtil.createMockBookDto2();

		mockDtoList.add(mock1);
		mockDtoList.add(mock2);

		when(this.bookService.findAll()).thenReturn(mockDtoList);

		try {
			this.mockMvc.perform(get(Endpoints.BOOKS)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].name").value(mock1.getName()))
					.andExpect(jsonPath("$[1].name").value(mock2.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void findByNameTest_succes() {
		List<BookDto2> mockDtoList = new ArrayList<>();
		BookDto2 mock1 = TestUtil.createMockBookDto2();
		BookDto2 mock2 = TestUtil.createMockBookDto2();
		String mockName = "Mocked Book";

		mockDtoList.add(mock1);
		mockDtoList.add(mock2);

		when(this.bookService.findByName(mockName)).thenReturn(mockDtoList);

		try {
			this.mockMvc.perform(get(Endpoints.BOOKS + "/find?name=" + mockName)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].name").value(mock1.getName()))
					.andExpect(jsonPath("$[1].name").value(mock2.getName()))
					.andExpect(jsonPath("$[0].description").value(mock1.getDescription()))
					.andExpect(jsonPath("$[1].description").value(mock2.getDescription()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void findByIdBookTest_success() {
		Long mockId = (long) 1;
		BookDto2 mock1 = TestUtil.createMockBookDto2();

		when(this.bookService.findId(mockId)).thenReturn(mock1);

		try {
			this.mockMvc.perform(get(Endpoints.BOOKS + "/" + mockId)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(mock1.getName()))
					.andExpect(jsonPath("$.description").value(mock1.getDescription()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void saveBookTest_success() {
		Book mock = TestUtil.createMockBook();
		Book createdMock = TestUtil.createMockBook();

		when(this.bookService.save(mock)).thenReturn(createdMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);
			this.mockMvc.perform(post(Endpoints.BOOKS).contentType(MediaType.APPLICATION_JSON).content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.description").value(createdMock.getDescription()))
					.andExpect(jsonPath("$.edition").value(createdMock.getEdition()))
					.andExpect(jsonPath("$.name").value(createdMock.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void updateBookTest_success() {
		Long mockId = (long) 1;
		Book mock = TestUtil.createMockBook();
		Book updatedMock = TestUtil.createMockBook();

		when(this.bookService.update(mockId, mock)).thenReturn(updatedMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);

			this.mockMvc
					.perform(put(Endpoints.BOOKS + "/" + mockId).contentType(MediaType.APPLICATION_JSON)
							.content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.description").value(updatedMock.getDescription()))
					.andExpect(jsonPath("$.edition").value(updatedMock.getEdition()))
					.andExpect(jsonPath("$.name").value(updatedMock.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
