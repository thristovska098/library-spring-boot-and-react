package mk.com.iwec.BookApp.source.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mk.com.iwec.BookApp.infrastructure.mapper.AbstractGeneralMapper;
import mk.com.iwec.BookApp.source.domain.Source;
import mk.com.iwec.BookApp.source.dto.SourceDto;

@Component
public class SourceMapper extends AbstractGeneralMapper implements SourceMapperInterface<SourceDto, Source> {

	@Autowired
	public SourceMapper(ModelMapper modelMapper) {
		super(modelMapper);

	}

	@Override
	public SourceDto mapEntitytoDto(Source source) {
		return this.modelMapper.map(source, SourceDto.class);
	}

}
