package com.bisaKhadayate.interfaces.services;

import java.util.List;
import java.util.Map;

import com.bisaKhadayate.bean.Advertise;
import com.bisaKhadayate.bean.User;

public interface ManageAdvertiseService {
	
	String uploadAdvertise(User user,Advertise advertise,String advertiseType);
	
	List<Advertise> getImageDetailByAdvertiseTypeAndDate(String advertiseType);
	
	List<Advertise> getImageDetailByAdvertiseType(String advertiseType);
	
	void deleteAdvertiseById(Integer id);

}
