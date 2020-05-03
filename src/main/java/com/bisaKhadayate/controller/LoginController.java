package com.bisaKhadayate.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;
import com.bisaKhadayate.interfaces.services.CreateUserService;
import com.bisaKhadayate.interfaces.services.ManageAdvertiseService;
import com.bisaKhadayate.interfaces.services.UserService;

@Controller
public class LoginController implements Constant {

	@Autowired
	UserService userService;
	
	@Autowired
	CreateUserService createUserService;
	
	@Autowired
	ManageAdvertiseService manageAdvertiseService;
	
	@Autowired
	CommonUserDetailService commonUserDetailService;

	@RequestMapping("BisaKhadayateMatrimonial")
	public String loginPage() {
		return LOGIN;
	}

	@RequestMapping(value = "userlogin", method = RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("username") String userName, @RequestParam("password") String password,
			HttpServletRequest request) {
		Map<String, String> userdetails = new HashMap<String, String>();
		ModelAndView mv = new ModelAndView();
		User user = userService.userLogin(userName, password);
		if (user == null) {
			mv.setViewName(LOGIN);
			mv.addObject("errorMsg", LOGIN_ERROR_MSG);
		} else {
			userdetails.put("name", user.getFirstName() + " " + user.getLastName());
			userdetails.put("username", user.getUserId());
			userdetails.put("password", user.getPassword());
			request.getSession().setAttribute("userDetails", user);
			mv.addObject("sliderAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(HOME_SLIDER_ADVERTISE));
			mv.addObject("homeMarqueeAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(HOME_MARQUEE_ADVERTISE));
			mv.setViewName(HOME);
			mv.addObject("user", user);
		}

		return mv;
	}

	@RequestMapping("logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return LOGIN;
	}

	@GetMapping(value = "home")
	public ModelAndView homePage(HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		String viewName = userdetails == null ? LOGIN : HOME;
		if (viewName == HOME) {
			mv.addObject("sliderAdvertiseList",	manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(HOME_SLIDER_ADVERTISE));
			mv.addObject("homeMarqueeAdvertiseList",manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(HOME_MARQUEE_ADVERTISE));
		}
		mv.setViewName(viewName);
		mv.addObject("user", userdetails);
		return mv;
	}

	@PostMapping(value = "forgotpassword/{emailId}")
	@ResponseBody
	public Map<String,String> forgotPassword(@PathVariable String emailId,HttpServletRequest request) {
		Integer userCount=userService.userDetailByEmail(emailId);
		Map<String,String> details = new HashMap<String,String>();
		if(userCount>0) {
			details.put("isEmailIdAvailable", "Y");
			String password = Base64.getEncoder().encodeToString(emailId.getBytes());
			createUserService.updateTemporaryPassword(password, emailId);
			boolean isMailSend=userService.sendMailForForgotPassword(emailId, password);
			details.put("isMailSend",isMailSend?"Y":"N");
		}else {
			details.put("isEmailAvailable", "N");
			details.put("Message", emailId+" is not available");
		}
		return details;
	}
	
	@PostMapping(value = "passwordchange")
	@ResponseBody
	public Map<String,String> passwordChange(@RequestBody Map<String,String> formData,HttpServletRequest request) {
		Integer userCount=userService.checkEmailIdAndTempPass(formData.get("generatedpassword"), formData.get("emailId"));;
		Map<String,String> details = new HashMap<String,String>();
		if(userCount>0) {
			details.put("isEmailAvailable", "Y");
			String newPasswordEncode  = Base64.getEncoder().encodeToString(formData.get("newpassword").getBytes());	
			userService.updatePassword(newPasswordEncode, formData.get("emailId"));
			details.put("Message", PASSWORD_CHANGE_MESSAGE);
		}else {
			details.put("isEmailAvailable", "N");
			details.put("Message", PASSWORD_CHANGES_WORNG_MAILID_MESSAGE);
		}
		return details;
	}
	
	
	@PostMapping(value = "userpasswordchange")
	@ResponseBody
	public Map<String,String> userPasswordChange(@RequestBody Map<String,String> formData,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userDetails");
		Integer userCount=userService.checkUserPass(Base64.getEncoder().encodeToString(formData.get("oldpassword").getBytes()),user.getUserId());
		Map<String,String> details = new HashMap<String,String>();
		
		if(userCount>0) {
			String newPasswordEncode  = Base64.getEncoder().encodeToString(formData.get("newpassword").getBytes());	
			userService.updatePassword(newPasswordEncode, user.getEmailId());
			details.put("isPasswordChange", "Y");
			details.put("Message", PASSWORD_CHANGE_MESSAGE);
		}else {
			details.put("isPasswordChange", "N");
			details.put("Message", WRONG_OLD_PASSWORD);
		}
		return details;
	}
}
