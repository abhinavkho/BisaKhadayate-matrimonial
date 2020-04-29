package com.bisaKhadayate.interfaces.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Advertise;

@Repository
public interface AdvertiseDao extends CrudRepository<Advertise, Integer> {
	
    @Query(value="SELECT * from Advertise a where CONVERT(a.startDate, DATE)<=CURDATE() and a.advertiseFolder = ?1 ", nativeQuery = true)
	List<Advertise> getImageDetailByAdvertiseTypeAndDate(String advertiseType);
    
    @Query(value="SELECT * from Advertise a where a.advertiseFolder = ?1 ", nativeQuery = true)
	List<Advertise> getImageDetailByAdvertiseType(String advertiseType);
    
    @Modifying
    @Query(value="DELETE FROM Advertise a WHERE CONVERT(a.endDate, DATE)<CURDATE()", nativeQuery = true)
    void deleteExpireAdvertise();
    
    @Query(value="select * FROM Advertise a WHERE CONVERT(a.endDate, DATE)<CURDATE()", nativeQuery = true)
    List<Advertise> getIAdvertiseDetailForDelete();
}
