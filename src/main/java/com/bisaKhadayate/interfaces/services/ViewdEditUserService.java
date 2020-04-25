package com.bisaKhadayate.interfaces.services;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.User;

public interface ViewdEditUserService {

	void updateUser(User user, HttpSession session);

	void uploadFile(User user, MultipartFile file);

	void deleteFile(String filePath);
}
