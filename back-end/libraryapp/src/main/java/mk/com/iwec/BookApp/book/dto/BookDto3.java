package mk.com.iwec.BookApp.book.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.iwec.BookApp.source.dto.SourceDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto3 {
	// this class will be shown for the authors books
	private Long bookId;
	private String name;
	private String description;
	private List<SourceDto> sources;

}
