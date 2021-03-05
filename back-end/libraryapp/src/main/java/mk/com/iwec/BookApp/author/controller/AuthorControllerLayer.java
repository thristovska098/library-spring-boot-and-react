package mk.com.iwec.BookApp.author.controller;

import java.util.List;

public interface AuthorControllerLayer<T, K, I> {
	public List<K> findAll();

	public I findId(String id);

	public void deleteId(String id);

	public T save(T t);

	public T update(String id, T t);

}
