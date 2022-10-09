package probono.service;

import java.util.ArrayList;

import probono.model.dto.Foreign;
import probono.model.dto.OpenActivity;
import probono.model.dto.Participant;

public class MatchingService {
	
	private static MatchingService instance = new MatchingService();
	
	//진행중인 Project를 저장하는 배열List 
	private ArrayList<OpenActivity> matchingServiceList = new ArrayList<>();
	//외국인 List
	private ArrayList<Foreign> foreignList = new ArrayList<>();
	
	private MatchingService () {}
	
	public static MatchingService getInstance() {
		return instance;
	}
	//전체 조회
	public ArrayList<OpenActivity> getMatchingServiceList() {
		return matchingServiceList;
	}
	
	
	//국적으로 외국인 검색
	public Foreign getForeignByCountry(String nationality) {
		for(OpenActivity a: matchingServiceList) {
			if(a.getActivityForeign().getNationality().equals(nationality)) {
				return a.getActivityForeign();
			}
		} return null;
		
	}
	//활동 이름으로 검색
	public OpenActivity getOpenActivity(String activityName) {
		for(OpenActivity a: matchingServiceList) {
			if(a.getActivityName().equals(activityName)) {
				return a;
			}
		} return null;
	}
	
	//외국인 배열 추가
	public void foreignInsert(Foreign foreign) throws Exception {
		for(Foreign f: foreignList) {
			if(foreign.getFname().equals(f.getFname())&&
					foreign.getFno()==f.getFno()) {
				throw new Exception("해당 외국인은 이미 존재합니다.");
			}
		} foreignList.add(foreign);
	}
	
	//활동 추가
	public void openActivityInsert(OpenActivity activity) throws Exception {
		OpenActivity a = 
				getOpenActivity(activity.getActivityName());
		if(a != null) {
			throw new Exception("해당 project명은 이미 존재합니다.");
			
		} matchingServiceList.add(activity);
	}
	
	//활동 삭제
	public void openActivityDelete(String activityName) {
		OpenActivity a1 = getOpenActivity(activityName);
		if(a1 != null) {
			matchingServiceList.remove(a1);
		}
	}
	//  외국인 변경하기
	public OpenActivity updateMatching(OpenActivity activity) {
		String wanttodo = activity.getActivityParticipant().getRequestType();
		for(Foreign fore:foreignList) {
			if(fore.getFrequestType().equals(wanttodo)) {
				activity.setActivityForeign(fore);
				return activity;
			}
		} return null;
	}
	//희망활동 맞는지 확인해보기
	public OpenActivity confirmMatching(OpenActivity activity) {
		Participant p = activity.getActivityParticipant();
		Foreign f = activity.getActivityForeign();
		if(p.getRequestType().equals(f.getFrequestType())) {
			return activity;
		} return null;
	}
}
