package com.bisaKhadayate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;
import com.bisaKhadayate.interfaces.services.ManageAdvertiseService;
import com.bisaKhadayate.interfaces.services.UserService;
import com.bisaKhadayate.interfaces.services.ViewdEditUserService;

@Controller
public class ViewdEditUserController implements Constant {

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("samaj")
	List<Samaj> samajList;

	
	@Autowired
	ViewdEditUserService viewdEditUserService;
	
	@Autowired
	CommonUserDetailService commonUserDetailService;
	
	@Autowired
	ManageAdvertiseService manageAdvertiseService;

	@RequestMapping(value = "editviewUser")
	public ModelAndView viewEditUserDetail(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		List<String> imagePathList = commonUserDetailService.getImagesFile(user);
		mv.addObject("filesName", imagePathList);
		Map<String, String> imagePathListMap = new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length - 1], imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap);
		mv.addObject("user", user);
		mv.addObject("gender", Character.toString(user.getGender()));
		mv.addObject("gotraList", commonUserDetailService.getGotraListBySamajId(user.getSamaj()));
		mv.addObject("samajList", samajList);
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(VIEW_PROFILE_ADDVERTISE));
		mv.setViewName(EDIT_UESR_DETAILS);
		return mv;
	}

	@RequestMapping(value = "updateuser", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody User userDetails, HttpServletRequest request) {
		viewdEditUserService.updateUser(userDetails, request.getSession());
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		userdetails = userService.userLogin(userDetails.getUserId(), userDetails.getPassword());
		request.getSession().setAttribute("userDetails", userdetails);
		return USER_UPDATED_MSG;
	}

	@RequestMapping(value = "uploadfile", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("fileupload") MultipartFile file, HttpSession session) {
		User user = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		viewdEditUserService.uploadFile(user, file);
		List<String> imagePathList = commonUserDetailService.getImagesFile(user);
		mv.addObject("filesName", imagePathList);
		Map<String, String> imagePathListMap = new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length - 1], imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap);
		mv.addObject("user", user);
		mv.addObject("gender", Character.toString(user.getGender()));
		mv.addObject("gotraList", commonUserDetailService.getGotraListBySamajId(user.getSamaj()));
		mv.addObject("samajList", samajList);
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(VIEW_PROFILE_ADDVERTISE));
		mv.setViewName(EDIT_UESR_DETAILS);
		return mv;
	}

	@RequestMapping(value = "deletephoto", method = RequestMethod.POST)
	public ModelAndView deletePhoto(@RequestParam("picname") String filePath, HttpSession session) {
		User user = (User) session.getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		viewdEditUserService.deleteFile(filePath);
		List<String> imagePathList = commonUserDetailService.getImagesFile(user);
		mv.addObject("filesName", imagePathList);
		Map<String, String> imagePathListMap = new HashMap<String, String>();
		for (String imagePath : imagePathList) {
			String image[] = imagePath.split("/");
			imagePathListMap.put(image[image.length - 1], imagePath);
		}
		mv.addObject("imagePathListMap", imagePathListMap);
		mv.addObject("user", user);
		mv.addObject("gender", Character.toString(user.getGender()));
		mv.addObject("gotraList", commonUserDetailService.getGotraListBySamajId(user.getSamaj()));
		mv.addObject("samajList", samajList);
		mv.addObject("viewProfileadvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(VIEW_PROFILE_ADDVERTISE));
		mv.setViewName(EDIT_UESR_DETAILS);
		return mv;
	}

}
