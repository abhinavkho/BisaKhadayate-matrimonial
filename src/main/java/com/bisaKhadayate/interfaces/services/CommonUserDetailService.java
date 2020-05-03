package com.bisaKhadayate.interfaces.services;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.bean.User;

public interface CommonUserDetailService {
	
	List<Gotra>  getGotraListBySamajId(Integer samajId);
	 
	List<Samaj> getSamaj();
	
	List<String> getImagesFile(User user);
	
	void sendmail(MultiValueMap<String, String> mailContent);
}
