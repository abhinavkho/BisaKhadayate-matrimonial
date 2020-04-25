package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.ViewdEditUserService;

@Service
public class ViewdEditUserServiceImpl implements ViewdEditUserService , Constant {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public void updateUser(User user, HttpSession session) {
		User userDetails = (User) session.getAttribute("userDetails");
		user.setId(userDetails.getId());
		user.setEmailId(userDetails.getEmailId());
		user.setPassword(userDetails.getPassword());
		user.setUserId(userDetails.getUserId());
		user.setIsActive(userDetails.getIsActive());
		user.setIsAdmin(userDetails.getIsAdmin());
		userDao.save(user);
	}

	@Override
	public void uploadFile(User user, MultipartFile file) {

		byte[] bytes;
		String fileName = file.getOriginalFilename();

		try {
			bytes = file.getBytes();
			File tmpDir = new ClassPathResource("/static").getFile();
			String path = tmpDir.getAbsolutePath() + "\\userImage\\" + user.getId() + "\\" + user.getFirstName()
					+ user.getLastName();
			tmpDir = new File(path);
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}
			Path pathObj = Paths.get(tmpDir.getAbsolutePath() + "\\" + fileName);
			Files.write(pathObj, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteFile(String filePath) {

		File tmpDir;
		try {
			tmpDir = new ClassPathResource("/static").getFile();
			String path = tmpDir.getAbsolutePath() + "\\" + filePath.replace("/", "\\");
			File file = new File(path);
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
