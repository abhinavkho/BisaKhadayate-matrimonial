package com.bisaKhadayate.interfaces.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Samaj;

@Repository
public interface SamajDao extends CrudRepository<Samaj,Integer> {

}
