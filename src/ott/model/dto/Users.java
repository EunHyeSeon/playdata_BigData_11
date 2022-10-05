package ott.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Users {

	private String id;
	private String name;
	private String password;
	private String genre_name;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id : ");
		builder.append(id);
		builder.append("\n이름 : ");
		builder.append(name);
		builder.append("\npw : ");
		builder.append("*".repeat(password.length()));
		builder.append("\n" + id + "님이 선호하는 장르 :  ");
		builder.append(genre_name);
		return builder.toString();
	}

}
