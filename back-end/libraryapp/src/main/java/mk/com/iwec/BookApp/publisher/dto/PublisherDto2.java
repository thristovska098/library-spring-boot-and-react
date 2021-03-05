package mk.com.iwec.BookApp.publisher.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.iwec.BookApp.book.dto.BookDto1;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto2 {
	private Long publisherId;
	private String name;
	private List<BookDto1> books;
}
