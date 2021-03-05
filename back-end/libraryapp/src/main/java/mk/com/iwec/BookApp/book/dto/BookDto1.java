package mk.com.iwec.BookApp.book.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto1 {
	// this class will be used for showing list of books
	private Long bookId;
	private String name;
	private List<AuthorDto1> authors;
}
