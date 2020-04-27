package com.bisaKhadayate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Advertise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="filename" ,nullable = false)
	private String fileName;
	
	@Column(name="filepath" ,nullable = false)
	private String filePath;
	
	@Column(name="startdate" ,nullable = false)
	private String startDate;
	
	@Column(name="enddate" ,nullable = false)
	private String endDate;
	
	@Column(name="advertisefolder" , nullable=false)
	private String advertiseFolder;
	
	@Transient
	private MultipartFile imageFile;
	
	
	
	public String getAdvertiseFolder() {
		return advertiseFolder;
	}

	public void setAdvertiseFolder(String advertiseFolder) {
		this.advertiseFolder = advertiseFolder;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
