package com.bisaKhadayate.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Constant {

	public final static List<String> URL_LIST = new ArrayList<String>(Arrays.asList("/","/css","js","/images"));
	
	public final static String HOME= "home";
	
	public final static String  LOGIN = "login";
	
	public final static String CREATE_USER = "createuser";
	
	public final static String  SEARCH_PAGE = "searchpage";
	
	public final static String  EDIT_UESR_DETAILS = "edituserdetail";
	
	public final static String CONTACT_US= "contact";

	public final static String ADVERTISE= "advertise";

	public final static String LOGIN_ERROR_MSG= "Username and password not match";
	
	public final static String FILTER = "F";
	
	public final static String NON_FILTER = "NF";
	
	public final static String USER_CREATED_MSG = "User created successfully";
	
	public final static String USER_UPDATED_MSG = "User updated successfully";
	
	public final static String MAIL_SUCCESS_MSG= "Mail has send successfully";
	
	public final static String MAIL_ERROR_MSG= "Not able to send email ";
}
