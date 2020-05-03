package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.bisaKhadayate.bean.Gotra;
import com.bisaKhadayate.bean.Samaj;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.GotraDao;
import com.bisaKhadayate.interfaces.dao.SamajDao;
import com.bisaKhadayate.interfaces.services.CommonUserDetailService;

@Service
public class CommonUserDetailServiceImpl  implements CommonUserDetailService , Constant{
	
	@Autowired
	GotraDao gotraDao;
	
	@Autowired
	SamajDao samajDao;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	@Transactional
	public List<Gotra> getGotraListBySamajId(Integer samajId) {
		return gotraDao.getGotraListBySamajId(samajId);
	}

	@Override
	@Transactional
	public List<Samaj> getSamaj() {
		return (List<Samaj>) samajDao.findAll();
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
	
	@Override
	public void sendmail(MultiValueMap<String, String> mailContent) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject("Test");
			mimeMessageHelper.setFrom(new InternetAddress("ab9khoti@gmail.com", "abhianv "));
			mimeMessageHelper.setTo("ab9khoti@gmail.com");
			mimeMessageHelper.setText("test");
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	
}
