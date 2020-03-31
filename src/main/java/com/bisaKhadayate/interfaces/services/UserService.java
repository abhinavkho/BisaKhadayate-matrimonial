package com.bisaKhadayate.interfaces.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bisaKhadayate.bean.User;

public interface UserService {
	
	 User userLogin(String userName , String password);
	 
	 List<Map<String,Object>> searchResult(Character gender);
	 
	 Optional<User> getUser(Integer id);
}
