package mk.com.iwec.BookApp.source.mapper;

public interface SourceMapperInterface<T, K> {
	public T mapEntitytoDto(K source);
}
