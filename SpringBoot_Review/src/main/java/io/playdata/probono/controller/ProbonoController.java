package io.playdata.probono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.playdata.probono.model.ProbonoService;
import io.playdata.probono.model.dto.ActivistDTO;
import io.playdata.probono.model.dto.RecipientDTO;


@Controller
@RequestMapping("probono")
public class ProbonoController {
	
	@Autowired
	ProbonoService probonoService;
	
	@RequestMapping(value = "/activist2", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView activist2(@RequestParam String activistId) throws Exception {
		ModelAndView mv = new ModelAndView();
		ActivistDTO activist = probonoService.getActivist(activistId);
		if (activist != null) {
			mv.addObject("activist", activist);
		} else {
			throw new Exception("존재하지 않는 기부자입니다.");
		}
		mv.setViewName("activist/activistDetail");
		return mv;
	}
	
	@RequestMapping(value = "/recipient2", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView recipient2(@RequestParam String recipientId) throws Exception {
		ModelAndView mv = new ModelAndView();
		RecipientDTO recipient = probonoService.getRecipient(recipientId);
		if (recipient != null) {
			mv.addObject("recipient", recipient);
		} else {
			throw new Exception("존재하지 않는 수혜자 입니다.");
		}
		mv.setViewName("recipient/recipientDetail");
		return mv;
	}

}
