package mk.com.iwec.BookApp.author.mapper;

import mk.com.iwec.BookApp.author.dto.*;
import mk.com.iwec.BookApp.infrastructure.mapper.AbstractGeneralMapper;
import mk.com.iwec.BookApp.infrastructure.mapper.GeneralMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mk.com.iwec.BookApp.author.domain.*;

@Component
public class AuthorMapper extends AbstractGeneralMapper implements GeneralMapper<AuthorDto1, AuthorDto2, Author> {

	@Autowired
	public AuthorMapper(ModelMapper modelMapper) {
		super(modelMapper);
	}

	@Override
	public AuthorDto1 entityToDto1(Author entity) {
		return this.modelMapper.map(entity, AuthorDto1.class);
	}

	@Override
	public Author dtoToEntity1(AuthorDto1 dto) {
		return this.modelMapper.map(dto, Author.class);
	}

	@Override
	public AuthorDto2 entityToDto2(Author entity) {
		return this.modelMapper.map(entity, AuthorDto2.class);
	}

	@Override
	public Author dtoToEntity2(AuthorDto2 dto) {
		return this.modelMapper.map(dto, Author.class);
	}

	@Override
	public Author entityUpdate(Author updated, Author original) {
		original.setBirthDate(updated.getBirthDate());
		original.setBooks(updated.getBooks());
		original.setLastName(updated.getLastName());
		original.setName(updated.getName());
		return original;
	}

}
