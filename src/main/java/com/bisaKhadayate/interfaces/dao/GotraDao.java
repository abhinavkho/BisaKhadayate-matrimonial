package com.bisaKhadayate.interfaces.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Gotra;

@Repository
public interface GotraDao extends CrudRepository<Gotra,Integer> {
	
	@Query(name="select * from Gotra g where g.id= ?1 ", nativeQuery = true)
	List<Gotra> getGotraListBySamajId(Integer samajId);

}
