package mk.com.iwec.BookApp.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.com.iwec.BookApp.book.dto.BookDto3;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto2 {

	private Long categoryId;
	private String name;
	private List<BookDto3> books;

}