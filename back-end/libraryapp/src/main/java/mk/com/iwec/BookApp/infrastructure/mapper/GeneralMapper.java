package mk.com.iwec.BookApp.infrastructure.mapper;

import java.util.List;

public interface GeneralMapper<Dto1, Dto2, Entity> {

	public Dto1 entityToDto1(Entity entity);

	public Entity dtoToEntity1(Dto1 dto);

	public Dto2 entityToDto2(Entity entity);

	public Entity dtoToEntity2(Dto2 dto);

	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass);

	public Entity entityUpdate(Entity e1, Entity e2);
}