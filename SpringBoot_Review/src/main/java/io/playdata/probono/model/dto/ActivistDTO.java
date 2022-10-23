/**
CREATE TABLE activist (
       activist_id          	VARCHAR2(20)  PRIMARY KEY,
       name               	VARCHAR2(20) NOT NULL,
       password         	VARCHAR2(20) NOT NULL,
       major                	VARCHAR2(50) NOT NULL
); */
package io.playdata.probono.model.dto;

import io.playdata.probono.model.entity.Activist;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActivistDTO {
	@ApiModelProperty(
	        name = "id"
	        , example = "giver1"
	    )
	@ApiParam(value = "기부자 ID", required = true)
	private String id;
	@ApiParam(value = "기부자 이름")
	private String name;
	@ApiParam(value = "기부자 비밀번호")
	private String password;
	@ApiParam(value = "기부자 전공")
	private String major;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(" 4. 재능 기부 분야 : ");
		builder.append(major);
		return builder.toString();
	}

	public Activist toEntity() {
		return Activist.builder().id(id).name(name).password(password).major(major).build();
	}

}
