package mk.com.iwec.BookApp.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
	public static final String BASE = "/api/";
	public static final String BOOKS = BASE + "books";
	public static final String AUTHORS = BASE + "authors";
	public static final String CATEGORIES = BASE + "categories";
	public static final String PUBLISHERS = BASE + "publishers";
}
