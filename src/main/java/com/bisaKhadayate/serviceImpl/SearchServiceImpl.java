package com.bisaKhadayate.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.bisaKhadayate.bean.User;
import com.bisaKhadayate.constant.Constant;
import com.bisaKhadayate.interfaces.dao.UserDao;
import com.bisaKhadayate.interfaces.services.SearchService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class SearchServiceImpl implements SearchService, Constant {

	@Autowired
	UserDao userDao;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void generatePdf(User user) {

		Document document = new Document();
		String pdfPath = "";
		try {
			File tmpDir = new ClassPathResource("/static").getFile();
			pdfPath = tmpDir.getAbsolutePath() + "\\pdf";
			tmpDir = new File(pdfPath);
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(pdfPath + "\\" + user.getFirstName() + user.getLastName() + ".pdf"));
			document.open();
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			String template = generateTemplate(user);
			worker.parseXHtml(writer, document, new StringReader(template));

			tmpDir = new ClassPathResource("/static").getFile();
			String imagePath = tmpDir.getAbsolutePath() + "\\userImage\\" + user.getId() + "\\" + user.getFirstName()
					+ user.getLastName();
			File folder = new File(imagePath);
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						try {
							Image img = Image.getInstance(imagePath + "\\" + listOfFiles[i].getName());
							img.scaleAbsolute(498f, 498f);
							document.add(img);
						} catch (Exception e) {
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
	public String generateTemplate(User user) {
		// TODO Auto-generated method stub
		StringBuilder template = new StringBuilder();
		template.append("<head>\r\n" + "	<style>\r\n" + "	table{\r\n" + "	padding-left: 500px;\r\n" + "	}\r\n"
				+ "	\r\n" + "	th{\r\n" + "	color:red;\r\n" + "	width:25%;\r\n" + "	}\r\n" + "	</style>\r\n"
				+ "</head>\r\n" + "<body> <div >		<center><h2>Bio data</h2></center>			\r\n"
				+ "					<table style=\"width: 100%;padding-left: 500px;\">\r\n"
				+ "						<tr>\r\n" + "							<th>Name</th>\r\n"
				+ "							<td  style=\" position: relative; left: 20%;\" ><span id=\"firstName_mdl\">"
				+ user.getFirstName() + "</span> <span id=\"lastName_mdl\">" + user.getLastName() + "</span></td>\r\n"
				+ "						</tr>\r\n" + "						<tr>\r\n"
				+ "							<th>Contact Number</th>\r\n"
				+ "							<td id=\"contactNumber_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getContactNumber() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Email id</th>\r\n"
				+ "							<td id=\"emailId_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getEmailId() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Height</th>\r\n"
				+ "							<td id=\"height_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getHeight() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Date of Birth</th>\r\n"
				+ "							<td id=\"dob_mdl\"  style=\" position: relative; left: 20%;\" >"
				+ user.getDob() + "</td>\r\n" + "						</tr>\r\n" + "						<tr>\r\n"
				+ "							<th>Gender</th>\r\n"
				+ "							<td id=\"gender_mdl\"  style=\" position: relative; left: 20%;\" >"
				+ user.getGender() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Colour</th>\r\n"
				+ "							<td id=\"colour_mdl\"  style=\" position: relative; left: 20%;\" >"
				+ user.getColour() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Samaj</th>\r\n"
				+ "							<td id=\"samaj_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getSamaj() + "</td>\r\n" + "						</tr>\r\n" + "						<tr>\r\n"
				+ "							<th>Birth place</th>\r\n"
				+ "							<td id=\"placeOfBirth_mdl\"  style=\" position: relative; left: 20%;\" >"
				+ user.getPlaceOfBirth() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Birth time</th>\r\n"
				+ "							<td id=\"time_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getTime() + "</td>\r\n" + "						</tr>\r\n" + "						<tr>\r\n"
				+ "							<th>Gotra</th>\r\n"
				+ "							<td id=\"gotra_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getGotra() + "</td>\r\n" + "						</tr>\r\n" + "						<tr>\r\n"
				+ "							<th>Current Address</th>\r\n"
				+ "							<td id=\"currentAddress_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getCurrentAddress() + "<br/></td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Parmanent Address</th>\r\n"
				+ "							<td id=\"permanentAddress_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getPermanentAddress() + "<br/></td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Education</th>\r\n"
				+ "							<td id=\"highestEducation_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getHighestEducation() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Occupation</th>\r\n"
				+ "							<td id=\"occupation_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getOccupation() + "</td>\r\n" + "						</tr>\r\n"
				+ "						<tr>\r\n" + "							<th>Family Details</th>\r\n"
				+ "							<td id=\"familyDetails_mdl\" style=\" position: relative; left: 20%;\" >"
				+ user.getFamilyDetails() + "</td>\r\n" + "						</tr>\r\n"
				+ "					</table>\r\n" + "					\r\n" + "				</div>	");
		template.append("</body>");
		return template.toString();
	}

	@Override
	public String sendmail() {
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
			return MAIL_ERROR_MSG;
		}
		return MAIL_SUCCESS_MSG;

	}

	@Override
	@Transactional
	public List<Map<String, Object>> searchFilterResult(Character gender, boolean isActive, String firstName,
			String lastName, String samaj, String gotra, int age, int index) {
		return userDao.searchFilterResult(gender, isActive, firstName, lastName, samaj, gotra, age, index);
	}

	@Override
	@Transactional
	public Integer searchFilterResultCount(Character gender, boolean isActive, String firstName, String lastName,
			String samaj, String gotra, int age) {
		return userDao.searchFilterResultCount(gender, isActive, firstName, lastName, samaj, gotra, age);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> searchResult(Character gender, int index) {
		return userDao.searchResult(gender, true, index);
	}

	@Override
	@Transactional
	public Integer searchResultCount(Character gender, boolean isActive) {
		return userDao.searchResultCount(gender, isActive);
	}

	@Override
	@Transactional
	public Optional<User> getUser(Integer id) {
		return userDao.findById(id);
	}

}
