package com.bisaKhadayate.interfaces.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.User;

@Repository
public interface UserDao extends CrudRepository<User,Integer>  {
	
	@Query("SELECT u from User u where u.userId = ?1 and u.password = ?2 ")
	User userLogin(String userName, String password);
	
	@Query("SELECT count(*) from User u where u.emailId = ?1 ")
	Integer checkDuplicateEmailId(String emailId);
	
	@Query("SELECT count(*) from User u where u.userId = ?1 ")
	 Integer checkDuplicateUsername(String username);
	
	@Query(value="Select u.id, u.firstName , u.lastName , u.dob , u.gotra , u.contactNumber ,u.gender , u.displayPic ,u.gotraname from User u where u.gender = ?1 and u.isactives = ?2 limit ?3,10", nativeQuery = true)
	List<Map<String,Object>> searchResult(Character gender,boolean isActive,int index );
	
	@Query(value="Select u.id, u.firstName , u.lastName , u.dob , u.gotra , u.contactNumber , u.gender , u.displayPic , u.gotraname from User u where u.gender = ?1 and u.isactives = ?2 and"
			+ " (1=CASE  WHEN ?3='' THEN 1 ELSE u.firstName=?3  END )"
			+ " and (1=CASE  WHEN ?4='' THEN 1 ELSE u.lastName=?4  END )"
			+ " and (1=CASE  WHEN ?5='' THEN 1 ELSE u.samaj=?5  END )"
			+ " and (1=CASE WHEN ?6=0 THEN 1 ELSE floor(datediff(CURDATE(),CONVERT(u.dob, DATE))/365)>=?6 END )limit ?7,10", nativeQuery = true)
	List<Map<String,Object>> searchFilterResult(Character gender,boolean isActive , String firstName , String lastName ,String samaj , int age,int index );

	
	@Query(value="Select count(*) from User u where u.gender = ?1 and u.isactives = ?2 ", nativeQuery = true)
	Integer searchResultCount(Character gender,boolean isActive);
	
	@Query(value="Select count(*) from User u where u.gender = ?1 and u.isactives = ?2 and"
			+ " (1=CASE  WHEN ?3='' THEN 1 ELSE u.firstName=?3  END )"
			+ " and (1=CASE  WHEN ?4='' THEN 1 ELSE u.lastName=?4  END )"
			+ " and (1=CASE  WHEN ?5='' THEN 1 ELSE u.samaj=?5  END )"
			+ " and (1=CASE WHEN ?6=0 THEN 1 ELSE floor(datediff(CURDATE(),CONVERT(u.dob, DATE))/365)>=?6 END )", nativeQuery = true)
	Integer searchFilterResultCount(Character gender,boolean isActive , String firstName , String lastName ,String samaj ,int age);

	@Query("SELECT count(*) from User u where u.emailId = ?1 ")
	Integer userDetailByEmail(String emailId);
	
	@Modifying
	@Query("UPDATE User u SET  u.temporaryPassword = ?1 where u.emailId = ?2")
	Integer updateTemporaryPassword(String temporaryPass,String emailId);
	
	@Query(value="Select count(*) from User u where u.temporaryPassword = ?1 and u.emailId = ?2 ", nativeQuery = true)
	Integer checkEmailIdAndTempPass(String temporaryPass,String emailId);

	@Modifying
	@Query("UPDATE User u SET  u.password=?1 , u.temporaryPassword='' where u.emailId = ?2")
	void updatePassword(String password,String emailId);
	
	
	@Query(value="Select count(*) from User u where u.password = ?1 and u.userId = ?2 ", nativeQuery = true)
	Integer checkUserPass(String password,String userId);
}
