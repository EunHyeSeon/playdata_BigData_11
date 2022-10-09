package probono.view;


import java.util.ArrayList;

import probono.controller.Controller;
import probono.model.dto.ActivityType;
import probono.model.dto.Foreign;
import probono.model.dto.OpenActivity;
import probono.model.dto.Participant;
import probono.service.MatchingService;

public class StartView {

	public static void main(String[] args) throws Exception{
		//참가자
		Participant participant1 = new Participant(1, "엄호용", "a@bb.com", "스포츠", "수원" );
		Participant participant2 = new Participant(2, "은혜선", "b@vv.com", "문화", "부천" );
		Participant participant3 = new Participant(3, "윤세종", "c@dd.com", "음식", "부천" );
		Participant participant4 = new Participant(4, "박아름샘", "d@hh.com", "음식", "서울" );

		//외국인
		Foreign foreign1 = new Foreign(11, "Luke", "ho@df.com", "스포츠", "프랑스", "서울");
		Foreign foreign2 = new Foreign(12, "Jane", "ui@df.com", "문화", "영국", "서울");
		Foreign foreign3 = new Foreign(13, "Charls", "th@th.com", "스포츠", "캐나다", "부산");
		Foreign foreign4 = new Foreign(14, "Samantha", "pl@we.com", "음식", "미국", "서울");
		//동일 사람 
		Foreign foreign5 = new Foreign(13, "Charls", "th@th.com", "스포츠", "캐나다", "부산");
		
		//활동영역
		ActivityType Sports = new ActivityType("Sport", "농구, 축구", "농구 경기, 축구관람"); 
		ActivityType Food = new ActivityType("Food", "한식, 양식", "맛집 탐방, 쿠킹 클래스"); 
		ActivityType Culture = new ActivityType("Culture", "영화, 뮤지컬", "영화관람, 미술관 관람"); 
		ActivityType Shopping = new ActivityType("Shopping", "의류, 식기, 가전기기", "백화점, 명동 나들이"); 
		ActivityType Study = new ActivityType("Study", "언어, 역사", "영어로 말하기, 역사 관련 다큐 관람"); 
		
		//활동 매칭
		OpenActivity activity1 = new OpenActivity("1_Sport", participant1, foreign1, Sports, "서울" ,"2022-03-01", "202-05-29", "농구시합" );
		OpenActivity activity2 = new OpenActivity("31_Food", participant4, foreign3, Food, "서울", "2022-05-01","2022-10-02","서울 내 맛집 탐방");
		OpenActivity activity3 = new OpenActivity("22_Culture", participant2,  foreign2, Culture, "서울", "2022-04-01", "2022-06-30", "영화 관람" );
		OpenActivity activity4 = new OpenActivity("25_Culture", participant2,foreign2, Culture, "서울", "2022-04-01", "2022-06-30", "영화 관람"  );
		//1번과 동일한 활동
		OpenActivity activity5 = new OpenActivity("1_Sport", participant1, foreign1, Sports, "서울" ,"2022-03-01", "202-05-29", "농구시합" );
		
		MatchingService service = MatchingService.getInstance(); 
		
		System.out.println("*** 01. acctivity 생성 ***");
		Controller.openActivityInsert(activity1);
		Controller.openActivityInsert(activity2);
		Controller.openActivityInsert(activity3);
		Controller.openActivityInsert(activity4);
		Controller.openActivityInsert(activity5);
		
		
		System.out.println("Foreign List 생성");
		Controller.foreignInsert(foreign1);
		Controller.foreignInsert(foreign2);
		Controller.foreignInsert(foreign3);
		Controller.foreignInsert(foreign4);
		Controller.foreignInsert(foreign5);
		
		System.out.println("\n*** 02. 모든 acctivity 검색 ***");
		ArrayList<OpenActivity> allMatchingList = service.getMatchingServiceList();
		EndView.projectListView(allMatchingList);	
		
		System.out.println("\n*** 03. '1_Sport' acctivity 검색 ***");
		OpenActivity activity = service.getOpenActivity("1_Sport");
		EndView.projectView(activity);
		
		System.out.println("\n*** 04. '25_Culture' acctivity 삭제 ***");
		service.openActivityDelete("25_Culture");
		activity = service.getOpenActivity("25_Culture");
		EndView.projectView(activity);
		
		System.out.println("\n ***특정 국적의 외국인의 activity 검색***");
		Foreign foreign = service.getForeignByCountry("영국");
		EndView.ForeignVeiw(foreign);
		
		
		System.out.println("\n***매칭 확인 후 올바르지 않은 매칭은 외국인 변경 ");
		Controller.confirmMatching(activity1);
		Controller.confirmMatching(activity2);
		Controller.confirmMatching(activity3);
	
	}

}
