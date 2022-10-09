package probono.controller;

import org.apache.log4j.Logger;

import probono.model.dto.Foreign;
import probono.model.dto.OpenActivity;
import probono.model.dto.Participant;
import probono.service.MatchingService;
import probono.view.EndView;
import probono.view.FailView;
import probono.view.SuccessView;



public class Controller {
	static Logger logger = Logger.getLogger("Log4jTest");
	
	private static MatchingService model = MatchingService.getInstance();
	
	public static void openActivityInsert(OpenActivity activity) {
		try {
			model.openActivityInsert(activity);
			SuccessView.succMsg("저장 완료");
		} catch (Exception e) {
//			e.printStackTrace();
//			FailView.failMsg("동일한 activity가 이미 존재합니다.");
			logger.info("동일한 activity가 이미 존재합니다.");
		}
	
	}
	
	public static void foreignInsert(Foreign foreign) {
		try {
			model.foreignInsert(foreign);
			SuccessView.succMsg("저장 완료");
		} catch (Exception e) {
//			e.printStackTrace();
//			FailView.failMsg("동일한 사람이 이미 존재합니다.");
			logger.info("동일한 사람이 이미 존재합니다.");
		}
	}
	
	public static void confirmMatching(OpenActivity activity) {
		Participant p = activity.getActivityParticipant();
		Foreign f = activity.getActivityForeign();
		
		if(p.getRequestType().equals(f.getFrequestType())) {
			model.confirmMatching(activity);
			SuccessView.succMsg("올바른 Matching - " + activity.getActivityName());
		}else {
			FailView.failMsg("희망 활동이 다르므로 Matching을 변경합니다. - " + activity.getActivityName());
			model.updateMatching(activity);
			logger.info("희망 활동이 다르므로 Matching을 변경함.");
			System.out.println("변경 후 Matching");
			EndView.projectView(activity);
		}
	}
}
