package com.bisaKhadayate.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.UserService;

@Service
public class UserServiceImpl implements UserService , Constant {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public User userLogin(String userName, String password) {
		return userDao.userLogin(userName, password);
	}

}
