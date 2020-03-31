package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.CreateUserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


@Service
public class CreateUserServiceImpl implements CreateUserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public Integer checkDuplicateEmailId(String emailId) {
		return userDao.checkDuplicateEmailId(emailId);
	}

	@Override
	@Transactional
	public Integer checkDuplicateUsername(String username) {
	//	userDao.
		return userDao.checkDuplicateUsername(username);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
		
	}

	@Override
	@Transactional
	public void updateUser(User user, HttpSession session) {
		// TODO Auto-generated method stub
		User userDetails=(User)session.getAttribute("userDetails");
		user.setId(userDetails.getId());
		user.setEmailId(userDetails.getEmailId());
		user.setPassword(userDetails.getPassword());
		user.setUserId(userDetails.getUserId());
		user.setIsActive(userDetails.getIsActive());
		user.setIsAdmin(userDetails.getIsAdmin());
		userDao.save(user);
	}

	@Override
	public void uploadFile(User user, String filePath,MultipartFile file) {

		  byte[] bytes;
		  String fileName = file.getOriginalFilename();
		
		  try {
				bytes = file.getBytes();
				
				File tmpDir = new File(filePath);
				tmpDir.delete();
			    if(!tmpDir.exists())
			    {
			    	tmpDir.mkdirs();
			    }
				Path pathObj = Paths.get(filePath+"\\"+fileName);
		        Files.write(pathObj, bytes);
		        
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}



	@Override
	public List<String> getImagesFile(User user, String filePath) {
		List<String> fileNameList = new ArrayList<String>();
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		String path = System.getProperty("user.home");
		String userFilePath = "userImage\\" + user.getId() + "\\" + user.getFirstName() + ""+ user.getLastName();
		if(listOfFiles!=null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					fileNameList.add(userFilePath + "\\" + listOfFiles[i].getName());
				}
			}
		}
		return fileNameList;
	}

	@Override
	public void generatePdf(User user, String filePath, String pdfPath) {
		
		Document document = new Document();
		try {
			File tmpDir = new File(pdfPath);
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(pdfPath + "" + user.getFirstName() + "_" + user.getLastName() + ".pdf"));
			document.open();
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			String template = generateTemplate(user, filePath);
			worker.parseXHtml(writer, document, new StringReader(template));

			File folder = new File(filePath);
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						try {
						Image img = Image.getInstance(filePath + "\\" + listOfFiles[i].getName());
						img.scaleAbsolute(498f, 498f);
						document.add(img);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
	}

	@Override
	public String generateTemplate(User user , String filePath) {
		// TODO Auto-generated method stub
		StringBuilder template = new StringBuilder();
		template.append("<head>\r\n" + 
				"	<style>\r\n" + 
				"	table{\r\n" + 
				"	padding-left: 500px;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	th{\r\n" + 
				"	color:red;\r\n" + 
				"	width:25%;\r\n" + 
				"	}\r\n" + 
				"	</style>\r\n" + 
				"</head>\r\n" + 
				"<body> <div >		<center><h2>Bio data</h2></center>			\r\n" + 
				"					<table style=\"width: 100%;padding-left: 500px;\">\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Name</th>\r\n" + 
				"							<td  ><span id=\"firstName_mdl\">"+user.getFirstName()+"</span> <span id=\"lastName_mdl\">"+user.getLastName()+"</span></td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Contact Number</th>\r\n" + 
				"							<td id=\"contactNumber_mdl\"  >"+user.getContactNumber()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Email id</th>\r\n" + 
				"							<td id=\"emailId_mdl\"  >"+user.getEmailId()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Height</th>\r\n" + 
				"							<td id=\"height_mdl\" >"+user.getHeight()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Date of Birth</th>\r\n" + 
				"							<td id=\"dob_mdl\">"+user.getDob()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Gender</th>\r\n" + 
				"							<td id=\"gender_mdl\">"+user.getGender()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Colour</th>\r\n" + 
				"							<td id=\"colour_mdl\">"+user.getColour()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Caste</th>\r\n" + 
				"							<td id=\"caste_mdl\">"+user.getCaste()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Birth place</th>\r\n" + 
				"							<td id=\"placeOfBirth_mdl\">"+user.getPlaceOfBirth()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Birth time</th>\r\n" + 
				"							<td id=\"time_mdl\">"+user.getTime()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Gotra</th>\r\n" + 
				"							<td id=\"gotra_mdl\">"+user.getGotra()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Current Address</th>\r\n" + 
				"							<td id=\"currentAddress_mdl\">"+user.getCurrentAddress()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Parmanent Address</th>\r\n" + 
				"							<td id=\"permanentAddress_mdl\">"+user.getPermanentAddress()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Education</th>\r\n" + 
				"							<td id=\"highestEducation_mdl\">"+user.getHighestEducation()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Occupation</th>\r\n" + 
				"							<td id=\"occupation_mdl\">"+user.getOccupation()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<th>Family Details</th>\r\n" + 
				"							<td id=\"familyDetails_mdl\">"+user.getFamilyDetails()+"</td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>\r\n" + 
				"					\r\n" + 
				"				</div>	");
		
		
		template.append("</body>");
		return template.toString();
	}

	@Override
	public void sendmail()  {
		
	}

	@Override
	public List<Map<String, Object>> searchFilterResult(Character gender,boolean isActive , String firstName , String lastName ,String caste , String gotra ) {
		// TODO Auto-generated method stub
		return userDao.searchFilterResult( gender, isActive ,  firstName ,  lastName , caste ,  gotra );
	}
		

}
