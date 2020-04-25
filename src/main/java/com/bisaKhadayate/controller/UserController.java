package com.bisaKhadayate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.Caste;
import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;
import com.bisaKhadayate.interfaces.services.CreateUserService;

@Controller
public class UserController implements Constant {

	@Autowired
	CreateUserService createUserService;

	@Autowired
	@Qualifier("caste")
	List<Caste> casteList;

	@Autowired
	@Qualifier("gotra")
	List<Gotra> gotraList;
	
	@Autowired
	CommonUserDetailService commonUserDetailService;

	@RequestMapping("createuser")
	public ModelAndView createUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.addObject("filesName", commonUserDetailService.getImagesFile(user));
		mv.addObject("gotraList", gotraList);
		mv.addObject("casteList", casteList);
		mv.addObject(user);
		mv.setViewName(CREATE_USER);
		return mv;
	}

	@RequestMapping(value = "checkDuplicateEmailId/{emailId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkDuplicateEmailId(@PathVariable String emailId) {
		return createUserService.checkDuplicateEmailId(emailId) == 1 ? true : false;
	}

	@RequestMapping(value = "checkDuplicateUsername/{username}", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkDuplicateUsername(@PathVariable String username) {
		return createUserService.checkDuplicateUsername(username) == 1 ? true : false;
	}

	@RequestMapping(value = "submituser", method = RequestMethod.POST)
	@ResponseBody
	public String submitUser(@RequestBody User userDetails, HttpServletRequest request) {
		createUserService.saveUser(userDetails);
		return USER_CREATED_MSG;
	}

	@GetMapping(value = "contactus")
	public ModelAndView contactus(HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(CONTACT_US);
		mv.addObject("user", userdetails);
		return mv;
	}

}
