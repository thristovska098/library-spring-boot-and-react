package mk.com.iwec.BookApp.publisher.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import lombok.*;
import mk.com.iwec.BookApp.book.domain.Book;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long publisherId;

	private String name;

	private String address;

	@JsonIgnore
	@OneToMany(mappedBy = "publisher", cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<Book> books;

}
