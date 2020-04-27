package com.bisaKhadayate.interfaces.services;

import java.util.List;

import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.User;

public interface CommonUserDetailService {
	
	List<Gotra> getGotra();
	 
	List<Samaj> getSamaj();
	
	List<String> getImagesFile(User user);

}
