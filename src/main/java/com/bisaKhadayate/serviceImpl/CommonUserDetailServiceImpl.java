package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bisaKhadayate.bean.Caste;
import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.CasteDao;
import com.bisaKhadayate.interfaces.dao.GotraDao;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;

@Service
public class CommonUserDetailServiceImpl  implements CommonUserDetailService , Constant{
	
	@Autowired
	GotraDao gotraDao;
	
	@Autowired
	CasteDao casteDao;

	@Override
	@Transactional
	public List<Gotra> getGotra() {
		return (List<Gotra>) gotraDao.findAll();
	}

	@Override
	@Transactional
	public List<Caste> getCaste() {
		return (List<Caste>) casteDao.findAll();
	}

	@Override
	public List<String> getImagesFile(User user) {
		List<String> fileNameList = new ArrayList<String>();
		try {
			File tmpDir = new ClassPathResource("/static").getFile();
			String path =tmpDir.getAbsolutePath()+"\\userImage\\"+user.getId()+"\\"+user.getFirstName()+user.getLastName();
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			String userFilePath = "userImage/" + user.getId() + "/" + user.getFirstName() + ""+ user.getLastName();
			if(listOfFiles!=null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						fileNameList.add(userFilePath + "/" + listOfFiles[i].getName());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileNameList;
	}
}
