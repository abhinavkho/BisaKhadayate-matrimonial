package com.bisaKhadayate.serviceImpl;

import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;
import com.bisaKhadayate.interfaces.services.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService, Constant {

	@Autowired
	UserDao userDao;
	
	@Autowired
	CommonUserDetailService commonUserDetailService;

	@Override
	@Transactional
	public Integer checkDuplicateEmailId(String emailId) {
		return userDao.checkDuplicateEmailId(emailId);
	}

	@Override
	@Transactional
	public Integer checkDuplicateUsername(String username) {
		return userDao.checkDuplicateUsername(username);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		user.setIsActive(true);
		user.setIsAdmin(false);
		user.setPassword(Base64.getEncoder().encodeToString(user.getUserId().getBytes()));
		userDao.save(user);
	}

	@Override
	@Transactional
	public void updateTemporaryPassword(String temporaryPass, String emailId) {
		userDao.updateTemporaryPassword(temporaryPass, emailId);
		
	}

}
