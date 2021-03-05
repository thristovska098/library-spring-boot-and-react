package mk.com.iwec.BookApp.publisher.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mk.com.iwec.BookApp.infrastructure.mapper.AbstractGeneralMapper;
import mk.com.iwec.BookApp.infrastructure.mapper.GeneralMapper;
import mk.com.iwec.BookApp.publisher.domain.*;
import mk.com.iwec.BookApp.publisher.dto.*;

@Component
public class PublisherMapper extends AbstractGeneralMapper
		implements GeneralMapper<PublisherDto1, PublisherDto2, Publisher> {

	@Autowired
	public PublisherMapper(ModelMapper modelMapper) {
		super(modelMapper);
	}

	@Override
	public PublisherDto1 entityToDto1(Publisher entity) {
		return this.modelMapper.map(entity, PublisherDto1.class);
	}

	@Override
	public Publisher dtoToEntity1(PublisherDto1 dto) {
		return this.modelMapper.map(dto, Publisher.class);
	}

	@Override
	public PublisherDto2 entityToDto2(Publisher entity) {
		return this.modelMapper.map(entity, PublisherDto2.class);
	}

	@Override
	public Publisher dtoToEntity2(PublisherDto2 dto) {
		return this.modelMapper.map(dto, Publisher.class);
	}

	@Override
	public Publisher entityUpdate(Publisher updated, Publisher original) {
		original.setAddress(updated.getAddress());
		original.setBooks(updated.getBooks());
		original.setName(updated.getName());
		return original;
	}

}
