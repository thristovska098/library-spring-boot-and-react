package mk.com.iwec.BookApp.author.service;

import java.util.List;

public interface AuthorServiceLayer<T, K, I> {
	public List<K> findAll();

	public I findId(String id);

	public void deleteId(String id);

	public T save(T t);

	public T update(String id, T t);

}
