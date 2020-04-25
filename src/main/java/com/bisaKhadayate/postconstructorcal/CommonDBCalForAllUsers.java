package com.bisaKhadayate.postconstructorcal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bisaKhadayate.bean.Caste;
import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;

@Configuration
public class CommonDBCalForAllUsers {
	
	@Autowired
	CommonUserDetailService commonUserDetail;
	
	
	@Bean(name="gotra")
	public List<Gotra> getGotra()
	{
		return commonUserDetail.getGotra();
	}
	
	@Bean(name="caste")
	public List<Caste> getCaste()
	{
		return commonUserDetail.getCaste();
	}

}
