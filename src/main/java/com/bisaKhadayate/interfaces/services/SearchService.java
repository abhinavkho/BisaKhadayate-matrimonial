package com.bisaKhadayate.interfaces.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bisaKhadayate.bean.User;

public interface SearchService {

	void generatePdf(User user);

	String generateTemplate(User user);

	String sendmail();

	List<Map<String, Object>> searchFilterResult(Character gender, boolean isActive, String firstName, String lastName,
			String samaj, String gotra, int age, int index);

	Integer searchFilterResultCount(Character gender, boolean isActive, String firstName, String lastName, String samaj,
			String gotra, int age);

	List<Map<String, Object>> searchResult(Character gender, int index);

	Integer searchResultCount(Character gender, boolean isActive);

	Optional<User> getUser(Integer id);

}
