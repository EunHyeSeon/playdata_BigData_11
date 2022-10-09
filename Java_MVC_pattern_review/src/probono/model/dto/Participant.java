package probono.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participant {
	/** 참여자 번호 */
	private int no;
	
	/** 참여자 이름 */
	private String name;
	
	/** 참여자 연락처 */
	private String contactInformation;
	
	/** 희망하는 활동 타입 */
	private String requestType;
	
	//거주지
	private String area;

	
	public Participant() {}
	public Participant(int no, String name, String contactInformation, String requestType, String area) {
		super();
		this.no = no;
		this.name = name;
		this.contactInformation = contactInformation;
		this.requestType = requestType;
		this.area = area;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("참여자 번호 : ");
		builder.append(no);
		builder.append(", 참여자 이름 : ");
		builder.append(name);
		builder.append(", 참여자 연락처 : ");
		builder.append(contactInformation);
		builder.append(", 참여자 거주지 : ");
		builder.append(area);		
		builder.append(", 참여자가 희망하는 활동 내용 : ");
		builder.append(requestType);
		return builder.toString();
	}

}
