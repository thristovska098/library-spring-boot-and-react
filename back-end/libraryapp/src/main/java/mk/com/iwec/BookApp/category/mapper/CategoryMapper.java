package mk.com.iwec.BookApp.category.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mk.com.iwec.BookApp.category.dto.*;
import mk.com.iwec.BookApp.category.domain.*;

import mk.com.iwec.BookApp.infrastructure.mapper.AbstractGeneralMapper;
import mk.com.iwec.BookApp.infrastructure.mapper.GeneralMapper;

@Component
public class CategoryMapper extends AbstractGeneralMapper
		implements GeneralMapper<CategoryDto1, CategoryDto2, Category> {

	@Autowired
	public CategoryMapper(ModelMapper modelMapper) {
		super(modelMapper);
	}

	@Override
	public CategoryDto1 entityToDto1(Category entity) {
		return this.modelMapper.map(entity, CategoryDto1.class);
	}

	@Override
	public Category dtoToEntity1(CategoryDto1 dto) {
		return this.modelMapper.map(dto, Category.class);
	}

	@Override
	public CategoryDto2 entityToDto2(Category entity) {
		return this.modelMapper.map(entity, CategoryDto2.class);
	}

	@Override
	public Category dtoToEntity2(CategoryDto2 dto) {
		return this.modelMapper.map(dto, Category.class);
	}

	@Override
	public Category entityUpdate(Category updated, Category original) {
		original.setBooks(updated.getBooks());
		original.setName(updated.getName());
		return original;
	}

}
