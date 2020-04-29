package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.Advertise;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.AdvertiseDao;
import com.bisaKhadayate.interfaces.services.ManageAdvertiseService;

@Service
public class ManageAdvertiseServiceImpl implements ManageAdvertiseService , Constant{
	
	@Autowired
	AdvertiseDao advertiseDao;

	@Override
	@Transactional
	public String uploadAdvertise(User user,Advertise advertise,String advertiseType) {
		
		byte[] bytes;
		try {
			File tmpDir = new ClassPathResource("/static").getFile();
			String basePath =tmpDir.getAbsolutePath() + "\\"+PROJECT_NAME+"\\"+ADVERTISE+"\\"+advertiseType; 
			
			advertise.setAdvertiseFolder(advertiseType);
			MultipartFile file=advertise.getImageFile();
			String fileName = file.getOriginalFilename();
			advertise.setFileName(fileName);
			bytes = file.getBytes();
			tmpDir = new File(basePath);
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}
			String filePath=tmpDir.getAbsolutePath() + "\\" + fileName;
			Path pathObj = Paths.get(filePath);
			Files.write(pathObj, bytes);
			advertise.setFilePath(PROJECT_NAME+"/"+ADVERTISE+"/"+advertiseType+"/"+fileName);
			advertiseDao.save(advertise);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return IMAGE_UPLOADED_SUCCESS;
	}

	@Override
	@Transactional
	public List<Advertise> getImageDetailByAdvertiseType(String advertiseType) {
		return advertiseDao.getImageDetailByAdvertiseType(advertiseType);
	}

	@Override
	@Transactional
	public List<Advertise> getImageDetailByAdvertiseTypeAndDate(String advertiseType) {
		return advertiseDao.getImageDetailByAdvertiseTypeAndDate(advertiseType);
	}

	@Override
	@Transactional
	public void deleteAdvertiseById(Integer id) {
		Optional<Advertise> advertise = advertiseDao.findById(id);
		if (advertise.isPresent()) {
			File tmpDir;
			try {
				tmpDir = new ClassPathResource("/static").getFile();
				String path = tmpDir.getAbsolutePath() + "\\" + advertise.get().getFilePath().replace("/", "\\");
				File file = new File(path);
				file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		advertiseDao.deleteById(id);

	}

}
