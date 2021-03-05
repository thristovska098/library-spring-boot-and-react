package mk.com.iwec.BookApp.category.controller;

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

import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.category.dto.CategoryDto2;
import mk.com.iwec.BookApp.category.service.CategoryService;
import mk.com.iwec.BookApp.infrastructure.Endpoints;
import mk.com.iwec.BookApp.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
class CategoryControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CategoryService service;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(mockMvc).isNotNull();
		assertThat(objectMapper).isNotNull();
	}

	@Test
	public void getAllCategoriesTest_succes() {
		List<CategoryDto2> mockDtoList = new ArrayList<>();
		CategoryDto2 mock1 = TestUtil.createMockCategoryDto2();
		CategoryDto2 mock2 = TestUtil.createMockCategoryDto2();

		mockDtoList.add(mock1);
		mockDtoList.add(mock2);

		when(this.service.findAll()).thenReturn(mockDtoList);

		try {
			this.mockMvc.perform(get(Endpoints.CATEGORIES)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].name").value(mock1.getName()))
					.andExpect(jsonPath("$[1].name").value(mock2.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void findByIdCategoryTest_success() {
		Long mockId = (long) 1;
		CategoryDto2 mock1 = TestUtil.createMockCategoryDto2();

		when(this.service.findId(mockId)).thenReturn(mock1);

		try {
			this.mockMvc.perform(get(Endpoints.CATEGORIES + "/" + mockId)).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(mock1.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void saveCategoryTest_success() {
		Category mock = TestUtil.createMockCategory();
		Category createdMock = TestUtil.createMockCategory();

		when(this.service.save(mock)).thenReturn(createdMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);
			this.mockMvc.perform(post(Endpoints.CATEGORIES).contentType(MediaType.APPLICATION_JSON).content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(createdMock.getName()));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void updateCategoryTest_success() {
		Long mockId = (long) 1;
		Category mock = TestUtil.createMockCategory();
		Category updatedMock = TestUtil.createMockCategory();

		when(this.service.update(mockId, mock)).thenReturn(updatedMock);

		try {
			String jsonBody = objectMapper.writer().writeValueAsString(mock);

			this.mockMvc
					.perform(put(Endpoints.CATEGORIES + "/" + mockId).contentType(MediaType.APPLICATION_JSON)
							.content(jsonBody))
					.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.name").value(updatedMock.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
