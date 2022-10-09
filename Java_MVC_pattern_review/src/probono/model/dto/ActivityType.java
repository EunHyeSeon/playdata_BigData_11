package probono.model.dto;

public class ActivityType {
	/** 활동 타입(종류) */
	private String activityType; 
	
	/** 활동 영역 */
	private String activityFeild;
	
	/** 활동 예시 */
	private String activityExample;

	public ActivityType() {}
	public ActivityType(String activityType, String activityFeild, String activityExample) {
		super();
		this.activityType = activityType;
		this.activityFeild = activityFeild;
		this.activityExample = activityExample;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("활동 타입 : ");
		builder.append(activityType);
		builder.append(", 활동 영역 : ");
		builder.append(activityFeild);
		builder.append(", 활동 예시 : ");
		builder.append(activityExample);
		return builder.toString();
	}
}
