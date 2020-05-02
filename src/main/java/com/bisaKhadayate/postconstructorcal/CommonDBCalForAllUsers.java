package com.bisaKhadayate.postconstructorcal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;

@Configuration
public class CommonDBCalForAllUsers {
	
	@Autowired
	CommonUserDetailService commonUserDetail;
		
	@Bean(name="samaj")
	public List<Samaj> getSamaj()
	{
		return commonUserDetail.getSamaj();
	}

}
