package com.bisaKhadayate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;

@Controller
public class ContactController implements Constant {

	@Autowired
	CommonUserDetailService commonUserDetailService;

	@GetMapping(value = "contactus")
	public ModelAndView contactus(HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(CONTACT_US);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping("sendMailtous")
	public ModelAndView sendMailToUs(@RequestBody MultiValueMap<String, String> mailContent,
			HttpServletRequest request) {
		commonUserDetailService.sendmail(mailContent);
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(CONTACT_US);
		mv.addObject("user", userdetails);
		return mv;
	}

}
