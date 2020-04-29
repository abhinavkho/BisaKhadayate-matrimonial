package com.bisaKhadayate.schedular;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bisaKhadayate.bean.Advertise;
import com.bisaKhadayate.interfaces.dao.AdvertiseDao;

@Component
public class SchedularServices {

	@Autowired
	AdvertiseDao advertiseDao;

	@Scheduled(cron = "0 52 18 * * *")
	@Transactional
	public void deleteAdvertise() {
		List<Advertise> advertiseList = advertiseDao.getIAdvertiseDetailForDelete();
		File tmpDir;
		for (Advertise advertise : advertiseList) {
			try {
				tmpDir = new ClassPathResource("/static").getFile();
				String path = tmpDir.getAbsolutePath() + "\\" + advertise.getFilePath().replace("/", "\\");
				File file = new File(path);
				file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		advertiseDao.deleteExpireAdvertise();
	}
}
