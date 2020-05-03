package com.bisaKhadayate.serviceImpl;

import java.util.Base64;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.UserService;

@Service
public class UserServiceImpl implements UserService , Constant {

	@Autowired
	UserDao userDao;
	

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	@Transactional
	public User userLogin(String userName, String password) {
		password=Base64.getEncoder().encodeToString(password.getBytes());
		return userDao.userLogin(userName, password);
	}

	@Override
	public Integer userDetailByEmail(String emailId) {
		return userDao.userDetailByEmail(emailId);
	}

	@Override
	public boolean sendMailForForgotPassword(String emailId,String password) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject("Bisa matrimonial chnages passWord");
			mimeMessageHelper.setFrom(new InternetAddress("ab9khoti@gmail.com", "Abhianv"));
			mimeMessageHelper.setTo(emailId);
			mimeMessageHelper.setText("Your temporary Password = "+password);
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public void updatePassword(String password,String emailId) {
		 userDao.updatePassword(password, emailId);
	}

	@Override
	@Transactional
	public Integer checkEmailIdAndTempPass(String temporaryPass, String emailId) {
		return userDao.checkEmailIdAndTempPass(temporaryPass, emailId);
	}

	@Override
	@Transactional
	public Integer checkUserPass(String password, String userId) {
		// TODO Auto-generated method stub
		return userDao.checkUserPass(password, userId);
	}

}
