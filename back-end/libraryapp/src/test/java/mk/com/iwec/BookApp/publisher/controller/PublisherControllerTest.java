package mk.com.iwec.BookApp.publisher.controller;

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

import mk.com.iwec.BookApp.infrastructure.Endpoints;
import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.publisher.dto.PublisherDto2;
import mk.com.iwec.BookApp.publisher.service.PublisherService;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
class PublisherControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	PublisherService publisherService;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(mockMvc).isNotNull();
		assertThat(objectMapper).isNotNull();
	}

	@Test
	public void getAllPublishersTest_succes() {
		List<PublisherDto2> mockDtoList = new ArrayList<>();
		PublisherDto2 mock1 = TestUtil.createMockPublisherDto2();
		PublisherDto2 mock2 = TestUtil.createMockPublisherDto2();

		mockDtoList.add(mock1);
		mockDtoList.add(mock2);

		when(this.publisherService.findAll()).thenReturn(mockDtoList);

		try {
			this.mockMvc.perform(get(Endpoints.PUBLISHERS)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].name").value(mock1.getName()))
					.andExpect(jsonPath("$[1].name").value(mock2.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void findByIdPublisherTest_success() {
		Long mockId = (long) 1;
		PublisherDto2 mock1 = TestUtil.createMockPublisherDto2();

		when(this.publisherService.findId(mockId)).thenReturn(mock1);

		try {
			this.mockMvc.perform(get(Endpoints.PUBLISHERS + "/" + mockId)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(mock1.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	public void savePublisherTest_success() {
		Publisher mock = TestUtil.createMockPublisher();
		Publisher createdMock = TestUtil.createMockPublisher();

		when(this.publisherService.save(mock)).thenReturn(createdMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);
			this.mockMvc.perform(post(Endpoints.PUBLISHERS).contentType(MediaType.APPLICATION_JSON).content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.address").value(createdMock.getAddress()))
					.andExpect(jsonPath("$.name").value(createdMock.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void updatePublisherTest_success() {
		Long mockId = (long) 1;
		Publisher mock = TestUtil.createMockPublisher();
		Publisher updatedMock = TestUtil.createMockPublisher();

		when(this.publisherService.update(mockId, mock)).thenReturn(updatedMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);

			this.mockMvc
					.perform(put(Endpoints.PUBLISHERS + "/" + mockId).contentType(MediaType.APPLICATION_JSON)
							.content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.address").value(updatedMock.getAddress()))
					.andExpect(jsonPath("$.name").value(updatedMock.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
