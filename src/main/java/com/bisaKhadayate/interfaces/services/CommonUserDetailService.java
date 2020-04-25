package com.bisaKhadayate.interfaces.services;

import java.util.List;

import com.bisaKhadayate.bean.Caste;
import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.User;

public interface CommonUserDetailService {
	
	List<Gotra> getGotra();
	 
	List<Caste> getCaste();
	
	List<String> getImagesFile(User user);

}
