package com.info.iosapplication.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.info.iosapplication.dao.MomentoAppDao;
import com.info.iosapplication.request.MomentoAppRequest;
import com.info.iosapplication.request.MomentoFileRequest;
import com.info.iosapplication.service.MomentoAppService;

@Service
public class MomentoAppServiceImpl implements MomentoAppService {

	@Autowired
	MomentoAppDao momentoAppDao;

	private static Logger log = LogManager.getLogger(MomentoAppServiceImpl.class);

	@Override
	public int insertValues(MomentoAppRequest momentoApp) {
		log.info("entered into insertValues in MomentoAppServiceImpl");
		return momentoAppDao.insertUser(momentoApp);
	}

	@Override
	public List<Map<String, Object>> getemails(String emailId, String password) {
		log.info("entered into getemails in MomentoAppServiceImpl with email : {}", emailId);
		try {
			List<Map<String, Object>> filteredEmails = new ArrayList<>();

			List<Map<String, Object>> emailList = momentoAppDao.fetchEmails();

			for (Map<String, Object> filteredList : emailList) {
				String passwordInDb = (String) filteredList.get("PASSWORD");

				String emailInDb = (String) filteredList.get("EMAIL_ID");

				if (emailId.equalsIgnoreCase(emailInDb) && password.equalsIgnoreCase(passwordInDb)) {
					filteredEmails.add(filteredList);
				}

			}
			log.info("completed fetching the user details");
			return filteredEmails;

		} catch (Exception e) {
			log.info("exception occured when trying to fetch user details with reason : {}", e.getMessage());
		}
		return null;
	}

	@Override
	public int insertFile(String emailId, MultipartFile file) {
		try {
			MomentoFileRequest momentoReq = new MomentoFileRequest();
			momentoReq.setEmailId(emailId);

			momentoReq.setFile(file.getBytes());
			return momentoAppDao.insertImage(momentoReq);

		} catch (IOException e) {
			log.info("exception occured when trying to insertfile with reason : {}", e.getMessage());

		}
		return 0;

	}
	
	public List<byte[]> retrievedBlobs(){
		System.out.println(momentoAppDao.getAllBlobData().size());
		 return momentoAppDao.getAllBlobData();
	}

}
