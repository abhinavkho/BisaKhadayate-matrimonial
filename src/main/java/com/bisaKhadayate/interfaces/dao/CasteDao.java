package com.bisaKhadayate.interfaces.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bisaKhadayate.bean.Caste;

@Repository
public interface CasteDao extends CrudRepository<Caste,Integer> {

}
