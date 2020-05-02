package com.bisaKhadayate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;
import com.bisaKhadayate.interfaces.services.ManageAdvertiseService;
import com.bisaKhadayate.interfaces.services.SearchService;

@Controller
public class SearchPageController implements Constant {

	@Autowired
	@Qualifier("samaj")
	List<Samaj> samajList;


	@Autowired
	CommonUserDetailService commonUserDetailService;

	@Autowired
	SearchService searchService;
	
	@Autowired
	ManageAdvertiseService manageAdvertiseService;

	@PostMapping(value = "getpendingdata")
	@ResponseBody
	public List<Map<String, Object>> getPendingData(@RequestBody Map<String, String> filterCriteria) {

		List<Map<String, Object>> searchResult = null;

		if (filterCriteria.get("searchType").equalsIgnoreCase(FILTER)) {
			searchResult = searchService.searchFilterResult(filterCriteria.get("gender").charAt(0), true,
					filterCriteria.get("firstName"), filterCriteria.get("lastName"), filterCriteria.get("samaj"),
					 Integer.parseInt(filterCriteria.get("age")),
					Integer.parseInt(filterCriteria.get("startIndex")));
		} else {
			searchResult = searchService.searchResult(filterCriteria.get("gender").charAt(0),
					Integer.parseInt(filterCriteria.get("startIndex")));
		}
		return searchResult;
	}

	@GetMapping(value = "download")
	@ResponseBody
	public void download(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Optional<User> userOptional = searchService.getUser(id);
		User user = userOptional.get();
		searchService.generatePdf(user);
		response.setContentType("application/pdf");
		PrintWriter out = response.getWriter();
		String filename = user.getFirstName() + user.getLastName() + ".pdf";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		File tmpDir = new ClassPathResource("/static").getFile();
		String pdfPath = tmpDir.getAbsolutePath() + "\\pdf";
		FileInputStream fileInputStream = new FileInputStream(pdfPath + "\\" + filename);
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	@PostMapping(value = "searchFilterResult")
	public ModelAndView searchFilterResult(@RequestBody MultiValueMap<String, String> formData,
			HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> searchResult = searchService.searchFilterResult(
				formData.get("filter_gender").get(0).charAt(0), true, formData.get("firstName").get(0),
				formData.get("lastName").get(0), formData.get("samaj").get(0),
				Integer.parseInt(formData.get("age").get(0)), 0);
		mv.addObject("userDetail", searchResult);
		mv.addObject("user", userdetails);
		mv.addObject("type", FILTER);
		mv.addObject("totalResult",
				searchService.searchFilterResultCount(formData.get("filter_gender").get(0).charAt(0), true,
						formData.get("firstName").get(0), formData.get("lastName").get(0), formData.get("samaj").get(0),
						Integer.parseInt(formData.get("age").get(0))));
		mv.addObject("gender", formData.get("filter_gender").get(0).charAt(0));
		mv.addObject("searchfilter", formData);
		mv.addObject("samajList", samajList);
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(SEARCH_PAGE_ADVERTISE));
		mv.setViewName(SEARCH_PAGE);
		return mv;
	}

	@PostMapping(value = "loadfullprofileofuser/{id}")
	@ResponseBody
	public User loadFullProfile(@PathVariable Integer id) {
		Optional<User> userOptional = searchService.getUser(id);
		User user = userOptional.get();
		user.setFileNameList(commonUserDetailService.getImagesFile(user));
		return user;
	}

	@PostMapping(value = "searchpage")
	public ModelAndView searchResult(@RequestParam Character gender, HttpServletRequest request) {
		User userdetails = (User) request.getSession().getAttribute("userDetails");
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> searchResult = searchService.searchResult(gender, 0);
		mv.addObject("userDetail", searchResult);
		mv.addObject("user", userdetails);
		mv.addObject("gender", gender);
		mv.addObject("type", NON_FILTER);
		mv.addObject("totalResult", searchService.searchResultCount(gender, true));
		mv.addObject("searchfilter", new HashMap<String, String>());
		mv.addObject("samajList", samajList);
		mv.addObject("searchAdvertiseList", manageAdvertiseService.getImageDetailByAdvertiseTypeAndDate(SEARCH_PAGE_ADVERTISE));
		mv.setViewName(SEARCH_PAGE);
		return mv;
	}

	@PostMapping(value = "email/{id}")
	@ResponseBody
	public String email(@PathVariable Integer id) {
		return searchService.sendmail();
	}

}
