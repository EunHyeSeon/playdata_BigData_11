package probono.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenActivity {
	
	/** 프로젝트 고유 이름 */
	private String activityName;

	/** 참여자 */
	private Participant activityParticipant;

	/** 외국인 참여자 */
	private Foreign activityForeign;

	/** 활동 타입 */
	private ActivityType activityType;
	
	//지역
	private String activityLoc;

	/** 프로젝트 시작일 */
	private String startDate;

	/** 프로젝트 종료일 */
	private String endDate;
	
	/** 진행되는 project의 상세 내용 */
	private String activityDetail;
	
	public OpenActivity() {
		super();
	};

	public OpenActivity(String activityName,  Participant activityParticipant,Foreign activityForeign,ActivityType activityType, 
			String activityLoc, String startDate, String endDate,
			String activityDetail) {
		//super();
		this.activityName = activityName;
		this.activityParticipant = activityParticipant;
		this.activityForeign = activityForeign;
		this.activityLoc = activityLoc;
		this.activityType = activityType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activityDetail = activityDetail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("활동명 : ");
		builder.append(activityName);
		builder.append(", 참여자 : ");
		builder.append(activityParticipant);
		builder.append(", 외국인 참여자 : ");
		builder.append(activityForeign);
		builder.append(", 활동 지역 : ");
		builder.append(activityLoc);
		builder.append(", 진행되는 활동 타입 : ");
		builder.append(activityType);
		builder.append(", 활동 시작일 : ");
		builder.append(startDate);
		builder.append(", 활동 종료일 : ");
		builder.append(endDate);
		builder.append(", 활동 상세 내용 : ");
		builder.append(activityDetail);
		return builder.toString();
	}
}
