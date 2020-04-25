package com.bisaKhadayate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;

@Controller
public class ManageAdvertiseController implements Constant {

	@GetMapping(value = "createadvertise")
	public ModelAndView createAdvertise(HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ADVERTISE);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping(value = "homeslideradvertise")
	public ModelAndView homeSliderAdvertise(@RequestParam("filehomeslideradvertise") MultipartFile file,
			HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ADVERTISE);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping(value = "homemarqueeadvertise")
	public ModelAndView homeMarqueeAdvertise(@RequestParam("filehomemarqueeadvertise") MultipartFile file,
			HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ADVERTISE);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping(value = "viewprofileadvertise")
	public ModelAndView viewProfileAdvertise(@RequestParam("filefileviewprofileadvertiseupload") MultipartFile file,
			HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ADVERTISE);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping(value = "searchpageadvertise")
	public ModelAndView searchPageAdvertise(@RequestParam("filesearchpageadvertise") MultipartFile file,
			HttpSession session) {
		User userdetails = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ADVERTISE);
		mv.addObject("user", userdetails);
		return mv;
	}

}
