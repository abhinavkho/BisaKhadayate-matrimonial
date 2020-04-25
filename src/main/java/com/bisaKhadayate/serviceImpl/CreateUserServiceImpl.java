package com.bisaKhadayate.serviceImpl;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService, Constant {

	@Autowired
	UserDao userDao;

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
		user.setPassword(generatePassword());
		userDao.save(user);
	}

	public String generatePassword() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

}
