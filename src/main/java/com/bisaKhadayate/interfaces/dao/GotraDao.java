package com.bisaKhadayate.interfaces.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Gotra;

@Repository
public interface GotraDao extends CrudRepository<Gotra,Integer> {
	
	

}
