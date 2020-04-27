package com.bisaKhadayate.interfaces.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Advertise;

@Repository
public interface AdvertiseDao extends CrudRepository<Advertise, Integer> {
	
    @Query(value="select * from Advertise a where a.advertiseFolder = ?1 ", nativeQuery = true)
	List<Advertise> getImageDetailByAdvertiseType(String advertiseType);
}
