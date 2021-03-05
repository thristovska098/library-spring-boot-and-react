package mk.com.iwec.BookApp.infrastructure.controller;

import java.util.List;

public interface ControllerLayer<T, I> {

	public List<I> findAll();

	public I findId(Long id);

	public void deleteId(Long id);

	public T save(T t);

	public T update(Long id, T t);

}
