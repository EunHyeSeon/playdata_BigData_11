package io.playdata.probono.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.playdata.probono.model.ProbonoService;
import io.playdata.probono.model.dto.ActivistDTO;
import io.playdata.probono.model.dto.ProbonoDTO;
import io.playdata.probono.model.dto.ProbonoProjectDTO;
import io.playdata.probono.model.dto.RecipientDTO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("rest")
public class ProbonoRestController {

	@Autowired
	ProbonoService probonoService;

	// 모든 ProbonoProject 검색 메소드
	@GetMapping("/probonoProjectAll")
	public List<ProbonoProjectDTO> probonoProjectAll() throws Exception {
		return probonoService.getAllProbonoProjects();
	}

	@GetMapping("/activistAll")
	public List<ActivistDTO> activistAll() throws Exception {

		return probonoService.getAllActivists();
	}

	// rest/activist?id="+v
	@GetMapping("/activist")
	public ActivistDTO activist(@RequestParam("id") String activistId) throws Exception {

		return probonoService.getActivist(activistId); // json 포멧으로 client의 js 요청 영역으로 출력
	}

	@GetMapping("/recipientAll")
	public List<RecipientDTO> recipientAll() throws Exception {
		return probonoService.getAllRecipients();
	}

	@GetMapping("/recipient")
	public RecipientDTO recipient(@RequestParam("id") String recipientID) throws Exception {
		return probonoService.getRecipient(recipientID);

	}

	@GetMapping("/probonoAll")
	public List<ProbonoDTO> probonoAll() throws Exception {
		return probonoService.getAllProbonos();
	}

	@PostMapping("/probonoProjectInsert")
	public boolean probonoProjectInsert(ProbonoProjectDTO probonoProject) throws Exception {
		if (probonoProject.getProbonoProjectName() != null && probonoProject.getProbonoId() != null
				&& probonoProject.getReceiveId() != null && probonoProject.getProjectContent() != null) {
			boolean result = probonoService.addProbonoProject(probonoProject);
			if (result) {
				return true;
			}
		} 
		return false;
	}

	@RequestMapping(value = "/activistInsert", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView activistInsert(@ModelAttribute("activist") ActivistDTO activistDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (activistDTO.getId() != null && activistDTO.getName() != null && activistDTO.getPassword() != null
				&& activistDTO.getMajor() != null) {
			boolean result = probonoService.addActivist(activistDTO);
			if (result) {
				mv.addObject("successMsg", "가입 완료");
			}
		}
		mv.setViewName("activist/activistDetail");
		return mv;
	}

	@RequestMapping(value = "/recipientInsert", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView recipientInsert(@ModelAttribute("recipient") RecipientDTO recipientDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (recipientDTO.getId() != null && recipientDTO.getName() != null && recipientDTO.getPassword() != null
				&& recipientDTO.getReceiveHopeContent() != null) {
			boolean result = probonoService.addRecipient(recipientDTO);
			if (result) {
				mv.addObject("successMsg", "가입 완료");
			}
		}
		mv.setViewName("recipient/recipientDetail");
		return mv;
	}

	// 재능 기부자 수정 요구
	@RequestMapping(value = "/activistUpdateReq", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView activistUpdateReq(@RequestParam String activistId) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("activist", probonoService.getActivist(activistId));
		mv.setViewName("activist/activistUpdate");
		return mv;
	}

	// 재능 기부자 수정 - 상세정보 확인 jsp[activistDetail.jsp]
	@RequestMapping(value = "/activistUpdate", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView activistUpdate(@RequestParam String activistId, @RequestParam String major) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (probonoService.updateActivist(activistId, major)) {
			mv.addObject("activist", probonoService.getActivist(activistId));
		} else {
			throw new Exception("수정  실패");
		}
		mv.setViewName("activist/activistDetail");
		return mv;
	}

	// 재능 기부자 삭제
	@RequestMapping(value = "/activistDelete", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView activistDelete(@RequestParam String activistId) {
		ModelAndView mv = new ModelAndView();
		try {
			if (probonoService.deleteActivist(activistId)) {
				mv.addObject("activistAll", probonoService.getAllActivists());
			} else {
				mv.addObject("errorMsg", "삭제 실패");
			}
		} catch (Exception s) {
			mv.addObject("errorMsg", s.getMessage());
			s.printStackTrace();
		}

		mv.setViewName("activist/activistList");
		return mv;
	}

	// 재능 수혜 수정 요구
	@RequestMapping(value = "/recipientUpdateReq", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView recipientUpdateReq(@RequestParam String recipientId) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("recipient", probonoService.getRecipient(recipientId));
		mv.setViewName("recipient/recipientUpdate");
		return mv;
	}

	// 재능 수혜자 수정 - 상세정보 확인 jsp[activistDetail.jsp]
	@RequestMapping(value = "/recipientUpdate", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView recipientUpdate(@RequestParam String recipientId, @RequestParam String receiveHopeContent) throws Exception {
		ModelAndView mv = new ModelAndView();
		String url = "showError";
		try {
			if (probonoService.updateRecipient(recipientId, receiveHopeContent)) {
				mv.addObject("recipient", probonoService.getRecipient(recipientId));
			} else {
				throw new Exception("수정  실패");
			}
		} catch (Exception s) {
			mv.addObject("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		mv.setViewName(url);
		return mv;
	}
	
	// 재능 수혜자 삭제
	@RequestMapping(value = "/recipientDelete", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView recipientDelete(@RequestParam String recipientId) {
		ModelAndView mv = new ModelAndView();
		String url = "showError";
		try {
			if (probonoService.deleteRecipient(recipientId)) {
				mv.addObject("recipientAll", probonoService.getAllRecipients());
				url = "recipient/recipientList";
			} else {
				mv.addObject("errorMsg", "삭제 실패");
			}
		} catch (Exception s) {
			mv.addObject("errorMsg", s.getMessage());
			s.printStackTrace();
		}
			mv.setViewName(url);
			return mv;
		}

	// 예외 처리 전담 메소드 (handler - 모든 예외를 처리하는메소드)
	@ExceptionHandler
	public ModelAndView handler(Exception e) {
		log.error("handlexception : {}", e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", e.getMessage());
		mv.setViewName("showError");

		return mv;
	}
	

}