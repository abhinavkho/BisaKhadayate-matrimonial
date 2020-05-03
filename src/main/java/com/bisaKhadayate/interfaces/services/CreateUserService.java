package com.bisaKhadayate.interfaces.services;

import com.bisaKhadayate.bean.User;

public interface CreateUserService {

	Integer checkDuplicateEmailId(String emailId);

	Integer checkDuplicateUsername(String username);

	void saveUser(User user);
	
	void updateTemporaryPassword(String temporaryPass,String emailId);

}
