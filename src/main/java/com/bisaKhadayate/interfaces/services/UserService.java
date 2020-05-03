package com.bisaKhadayate.interfaces.services;

import com.bisaKhadayate.bean.User;

public interface UserService {

	User userLogin(String userName, String password);
	
	Integer userDetailByEmail(String emailId);
	
	boolean sendMailForForgotPassword(String emailId,String password);
	
	Integer checkEmailIdAndTempPass(String temporaryPass,String emailId);
	
	void updatePassword(String password,String emailId);
	
	Integer checkUserPass(String password,String userId);

}
