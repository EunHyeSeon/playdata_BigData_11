package ott.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contents {
	private String contents_name;
	private String contents_actor;
	private String contents_inform;
	private String contents_type;
	private String genre_name;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("| 제목 |  " + contents_name );
		builder.append("\n| 타입 |  " + contents_type );
		builder.append("\n| 장르 |  " + genre_name );
		builder.append("\n| 배우 |  " + contents_actor );
		builder.append("\n| 소개 |  \n " + contents_inform );

		return builder.toString();
		

	}

}
