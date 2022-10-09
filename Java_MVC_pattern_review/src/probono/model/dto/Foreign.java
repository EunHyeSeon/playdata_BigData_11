package probono.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Foreign {
	/** 외국인 번호 */
	private int fno;
	
	/** 외국인 이름 */
	private String fname;
	
	/** 외국인 연락처 */
	private String fcontactInformation;
	
	/** 혜택받길 희망하는 활동 타입 */
	private String frequestType;
	
	/** 국적*/
	private String nationality;
	
	//거주지
	private String farea;

	
	public Foreign() {}
	public Foreign(int no, String name, String contactInformation, 
			String requestType, String nationality, String farea) {
		super();
		this.fno = no;
		this.fname = name;
		this.fcontactInformation = contactInformation;
		this.frequestType = requestType;
		this.nationality = nationality;
		this.farea = farea;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("외국인 번호 : ");
		builder.append(fno);
		builder.append(", 외국인 이름 : ");
		builder.append(fname);
		builder.append(", 외국인 연락처 : ");
		builder.append(fcontactInformation);
		builder.append(", 출신국가 : ");
		builder.append(nationality);
		builder.append(", 교류 활동 지역 : ");
		builder.append(farea);
		builder.append(", 외국인이 희망하는 활동 내용 : ");
		builder.append(frequestType);
		return builder.toString();
	}
}
