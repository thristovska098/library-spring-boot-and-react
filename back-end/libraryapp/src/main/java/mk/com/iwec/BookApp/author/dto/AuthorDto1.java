package mk.com.iwec.BookApp.author.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto1 {
	// this class will be shown for short information for the author
	private String ssn;
	private String name;
	private String lastName;
}
