package com.bisaKhadayate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.Advertise;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.ManageAdvertiseService;

@Controller
public class ManageAdvertiseController implements Constant {
	
	@Autowired
	ManageAdvertiseService manageAdvertiseService;

	@GetMapping(value = "createadvertise")
	public ModelAndView createAdvertise(HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userdetails);
		mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_SLIDER_ADVERTISE));
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(VIEW_PROFILE_ADDVERTISE));
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(SEARCH_PAGE_ADVERTISE));
		mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_MARQUEE_ADVERTISE));
		mv.setViewName(ADVERTISE);
		return mv;
	}

	@PostMapping(value = "homeslideradvertise")
	public ModelAndView homeSliderAdvertise(@ModelAttribute Advertise advertise,HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		manageAdvertiseService.uploadAdvertise(userdetails, advertise,HOME_SLIDER_ADVERTISE);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userdetails);
		mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_SLIDER_ADVERTISE));
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(VIEW_PROFILE_ADDVERTISE));
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(SEARCH_PAGE_ADVERTISE));
		mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_MARQUEE_ADVERTISE));
		mv.setViewName(ADVERTISE);
		return mv;
	}

	@PostMapping(value = "homemarqueeadvertise")
	public ModelAndView homeMarqueeAdvertise(@ModelAttribute Advertise advertise,HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		manageAdvertiseService.uploadAdvertise(userdetails, advertise,HOME_MARQUEE_ADVERTISE);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userdetails);
		mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_SLIDER_ADVERTISE));
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(VIEW_PROFILE_ADDVERTISE));
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(SEARCH_PAGE_ADVERTISE));
		mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_MARQUEE_ADVERTISE));
		mv.setViewName(ADVERTISE);
		return mv;
	}

	@PostMapping(value = "viewprofileadvertise")
	public ModelAndView viewProfileAdvertise(@ModelAttribute Advertise advertise,HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		manageAdvertiseService.uploadAdvertise(userdetails, advertise,VIEW_PROFILE_ADDVERTISE);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userdetails);
		mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_SLIDER_ADVERTISE));
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(VIEW_PROFILE_ADDVERTISE));
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(SEARCH_PAGE_ADVERTISE));
		mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_MARQUEE_ADVERTISE));
		mv.setViewName(ADVERTISE);
		return mv;
	}

	@PostMapping(value = "searchpageadvertise")
	public ModelAndView searchPageAdvertise(@ModelAttribute Advertise advertise,HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		manageAdvertiseService.uploadAdvertise(userdetails, advertise,SEARCH_PAGE_ADVERTISE);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userdetails);
		mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_SLIDER_ADVERTISE));
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(VIEW_PROFILE_ADDVERTISE));
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(SEARCH_PAGE_ADVERTISE));
		mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseType(HOME_MARQUEE_ADVERTISE));
		mv.setViewName(ADVERTISE);
		return mv;
	}

}
