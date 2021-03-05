package mk.com.iwec.BookApp.utils;

import java.util.Date;

import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;
import mk.com.iwec.BookApp.author.dto.AuthorDto2;
import mk.com.iwec.BookApp.book.domain.Book;
import mk.com.iwec.BookApp.book.dto.BookDto2;
import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.category.dto.CategoryDto2;
import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.publisher.dto.PublisherDto2;
import mk.com.iwec.BookApp.source.domain.Source;

public class TestUtil {

	public static Source createMockSource() {
		Source source = new Source();
		source.setImgSrc("Source IMAGESOURCE");
		source.setFormat("Source FORMAT");
		source.setUrl("Source URL");
		return source;
	}

	public static Publisher createMockPublisher() {
		Publisher publisher = new Publisher();
		publisher.setAddress("Publishers address");
		publisher.setName("Mock Publisher");
		return publisher;
	}

	public static Category createMockCategory() {
		Category mock = new Category();
		mock.setBooks(null);
		mock.setName("Category Name");
		return mock;
	}

	public static Author createMockAuthor() {
		Author mock = new Author();
		mock.setSsn("0000000000000");
		mock.setName("Mock firstName");
		mock.setLastName("Mock LastName");
		mock.setBooks(null);
		mock.setBirthDate(new Date());
		return mock;

	}

	public static Book createMockBook() {
		Book mock = new Book();
		mock.setAuthors(null);
		mock.setCategories(null);
		mock.setDescription("Description for mocked book...");
		mock.setEdition((long) 2);
		mock.setName("Mocked Book");
		mock.setPublisher(null);
		mock.setSources(null);
		return mock;
	}

	public static BookDto2 createMockBookDto2() {
		BookDto2 book = new BookDto2();
		book.setName("Mocked Book");
		book.setAuthors(null);
		book.setDescription("Mocked Description");
		book.setSources(null);
		return book;
	}

	public static AuthorDto1 createMockAuthorDto1() {
		AuthorDto1 author = new AuthorDto1();
		author.setSsn("0000000000000");
		author.setLastName("Mock LastName");
		author.setName("Mock Name");
		return author;

	}

	public static AuthorDto2 createMockAuthorDto2() {
		AuthorDto2 author = new AuthorDto2();
		author.setBooks(null);
		author.setSsn("0000000000000");
		author.setLastName("Mock LastName");
		author.setName("Mock Name");
		return author;
	}

	public static PublisherDto2 createMockPublisherDto2() {
		PublisherDto2 publisher = new PublisherDto2();
		publisher.setName("Publisher Mock Name");
		publisher.setBooks(null);
		return publisher;
	}

	public static CategoryDto2 createMockCategoryDto2() {
		CategoryDto2 category = new CategoryDto2();
		category.setName("Mock name");
		category.setCategoryId((long) 1);
		return category;
	}

}
