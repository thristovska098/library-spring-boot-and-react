package mk.com.iwec.BookApp.book.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.iwec.BookApp.author.dto.AuthorDto1;
import mk.com.iwec.BookApp.source.dto.SourceDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto2 {
	// this class will be used for showing individual book
	private Long bookId;
	private String name;
	private List<AuthorDto1> authors;
	private String description;
	private List<SourceDto> sources;
}
