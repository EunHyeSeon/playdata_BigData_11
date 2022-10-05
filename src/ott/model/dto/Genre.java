package ott.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Genre {
	private String genre_name;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(genre_name+" 입니다.");
		return builder.toString();
	}
}
