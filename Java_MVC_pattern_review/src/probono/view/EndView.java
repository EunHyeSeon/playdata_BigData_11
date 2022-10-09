package probono.view;

import java.util.ArrayList;

import probono.model.dto.Foreign;
import probono.model.dto.OpenActivity;

public class EndView {
	//진행중인 특정 프로젝트 출력
	public static void projectView(OpenActivity activity) {
		if(activity != null) {
			System.out.println(activity);		
		}else {
			System.out.println("해당 activity는 존재하지 않습니다.");
		}
	}
	
	//진행중인 모든 프로젝트 출력
	public static void projectListView(ArrayList<OpenActivity> allMatchingList){
		int index = 1;
		for(OpenActivity activity : allMatchingList){			
			if(activity != null){
				System.out.println("[진행 중인 activity : " + (index++) + "] " + activity);
			}
		}
	}
	//Foreign 출력
	public static void ForeignVeiw(Foreign foreign) {
		System.out.println(foreign.toString());
	}

}
