package mk.com.iwec.BookApp.source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceDto {
	private Long sourceId;
	private String url;
	private String imgSrc;

}