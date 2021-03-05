package mk.com.iwec.BookApp.book.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.dto.BookDto1;
import mk.com.iwec.BookApp.book.dto.BookDto2;
import mk.com.iwec.BookApp.infrastructure.mapper.AbstractGeneralMapper;
import mk.com.iwec.BookApp.infrastructure.mapper.GeneralMapper;

@Component
public class BookMapper extends AbstractGeneralMapper implements GeneralMapper<BookDto1, BookDto2, Book> {

	@Autowired
	public BookMapper(ModelMapper modelMapper) {
		super(modelMapper);
	}

	@Override
	public BookDto1 entityToDto1(Book entity) {
		return this.modelMapper.map(entity, BookDto1.class);
	}

	@Override
	public Book dtoToEntity1(BookDto1 dto) {
		return this.modelMapper.map(dto, Book.class);
	}

	@Override
	public BookDto2 entityToDto2(Book entity) {
		return this.modelMapper.map(entity, BookDto2.class);
	}

	@Override
	public Book dtoToEntity2(BookDto2 dto) {
		return this.modelMapper.map(dto, Book.class);
	}

	@Override
	public Book entityUpdate(Book updated, Book original) {
		original.setAuthors(updated.getAuthors());
		original.setCategories(updated.getCategories());
		original.setDescription(updated.getDescription());
		original.setEdition(updated.getEdition());
		original.setName(updated.getName());
		original.setPublisher(updated.getPublisher());
		original.setSources(updated.getSources());
		return original;
	}

	/*
	 * public List<BookDto3> mapBooksForAuthor(List<Book> books) { return
	 * books.stream().map(b ->
	 * this.modelMapper.map(b,BookDto3.class)).collect(Collectors.toList()); }
	 */

}
