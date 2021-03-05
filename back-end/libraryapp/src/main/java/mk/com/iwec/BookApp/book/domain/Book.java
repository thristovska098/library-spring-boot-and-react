package mk.com.iwec.BookApp.book.domain;

import java.util.List;
import javax.persistence.*;
import lombok.*;
import mk.com.iwec.BookApp.author.domain.Author;
import mk.com.iwec.BookApp.category.domain.Category;
import mk.com.iwec.BookApp.publisher.domain.Publisher;
import mk.com.iwec.BookApp.source.domain.Source;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long bookId;

	@NonNull
	@Column(length = 200)
	private String name;

	@NonNull
	private Long edition;

	@NonNull
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Source> sources;

	@ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn
	private Publisher publisher;

	@ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "book_from_authors", joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "ssn"))
	private List<Author> authors;

	@ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "bookId"), inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private List<Category> categories;

}