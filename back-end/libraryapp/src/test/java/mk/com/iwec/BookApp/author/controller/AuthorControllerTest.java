package mk.com.iwec.BookApp.author.controller;

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

import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;
import mk.com.iwec.BookApp.author.dto.AuthorDto2;
import mk.com.iwec.BookApp.author.service.*;
import mk.com.iwec.BookApp.infrastructure.Endpoints;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
class AuthorControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	AuthorService service;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(mockMvc).isNotNull();
		assertThat(objectMapper).isNotNull();
	}

	@Test
	public void getAllAuthorsTest_success() {
		List<AuthorDto1> mockDtoList = new ArrayList<>();
		AuthorDto1 mock1 = TestUtil.createMockAuthorDto1();
		AuthorDto1 mock2 = TestUtil.createMockAuthorDto1();

		mockDtoList.add(mock1);
		mockDtoList.add(mock2);

		when(service.findAll()).thenReturn(mockDtoList);

		try {
			this.mockMvc.perform(get(Endpoints.AUTHORS)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].name").value(mock1.getName()))
					.andExpect(jsonPath("$[0].lastName").value(mock1.getLastName()))
					.andExpect(jsonPath("$[1].name").value(mock2.getName()))
					.andExpect(jsonPath("$[1].lastName").value(mock2.getLastName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void findByIdAuthorTest_success() {
		String mockSsn = "0000000000000";
		AuthorDto2 mock = TestUtil.createMockAuthorDto2();

		when(service.findId(mockSsn)).thenReturn(mock);

		try {
			this.mockMvc.perform(get(Endpoints.AUTHORS + "/" + mockSsn)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(mock.getName()))
					.andExpect(jsonPath("$.lastName").value(mock.getLastName()))
					.andExpect(jsonPath("$.ssn").value(mock.getSsn()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void saveAuthorTest_success() {
		Author mock = TestUtil.createMockAuthor();
		Author createdMock = TestUtil.createMockAuthor();

		when(service.save(mock)).thenReturn(createdMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);
			this.mockMvc.perform(post(Endpoints.AUTHORS).contentType(MediaType.APPLICATION_JSON).content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(createdMock.getName()))
					.andExpect(jsonPath("$.lastName").value(createdMock.getLastName()))
					.andExpect(jsonPath("$.ssn").value(createdMock.getSsn()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void updateAuthorTest_succes() {
		Author mock = TestUtil.createMockAuthor();
		Author updatedMock = TestUtil.createMockAuthor();
		String mockSsn = "0000000000000";

		when(service.update(mockSsn, mock)).thenReturn(updatedMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);
			this.mockMvc
					.perform(put(Endpoints.AUTHORS + "/" + mockSsn).contentType(MediaType.APPLICATION_JSON)
							.content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(updatedMock.getName()))
					.andExpect(jsonPath("$.lastName").value(updatedMock.getLastName()))
					.andExpect(jsonPath("$.ssn").value(updatedMock.getSsn()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
