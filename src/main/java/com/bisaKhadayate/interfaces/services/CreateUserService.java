package com.bisaKhadayate.interfaces.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.User;

public interface CreateUserService {

	Integer checkDuplicateEmailId(String emailId);

	Integer checkDuplicateUsername(String username);

	void saveUser(User user);

	void updateUser(User user, HttpSession session);

	void uploadFile(User user, String path, MultipartFile file);
	
	List<String> getImagesFile(User user, String path);
	
	void generatePdf(User user,String filePath,String pdfPath);
	
	String generateTemplate(User user,String filePath);
	
	void sendmail() ;
	
	List<Map<String,Object>> searchFilterResult(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra );

}
