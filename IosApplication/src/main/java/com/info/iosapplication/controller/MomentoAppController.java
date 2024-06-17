package com.info.iosapplication.controller;


import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.info.iosapplication.request.MomentoAppRequest;
import com.info.iosapplication.response.MomentoAppResponse;
import com.info.iosapplication.service.MomentoAppService;


@RestController
public class MomentoAppController {
	
	@Autowired
	MomentoAppService momentoAppService;
	
	private static Logger log = LogManager.getLogger(MomentoAppController.class);
	
	@PostMapping("/signup")
	public String insertRow(@RequestBody MomentoAppRequest momentoRequest) {
		log.info("entered into insertRow in MomentoAppController");
		
		
		int rowsInserted = momentoAppService.insertValues(momentoRequest);
		
		if(rowsInserted == 1) {
			log.info("Exited From insertRow in MomentoAppController");
			return "row inserted Successfully";
		}
		return "data not inserted";
	}
	
	@GetMapping("/login")
    public MomentoAppResponse userDetails(@RequestParam String emailId, @RequestParam String password){
		MomentoAppResponse momentoResponse = new MomentoAppResponse();
		List<Map<String,Object>> serviceList = momentoAppService.getemails(emailId, password);
	     List<byte[]> userPictures = momentoAppService.retrievedBlobs();
		if(serviceList != null && !serviceList.isEmpty()) {
			momentoResponse.setUserDetailsList(serviceList);
			momentoResponse.setUploadedImages(userPictures);
		return 	momentoResponse;
		}
		return null;
		
   }

	@PostMapping("/upload")
	public String uploadImage(@RequestParam String emailId, @RequestParam MultipartFile file) {
		int fileInsterted = momentoAppService.insertFile(emailId, file);
		if(fileInsterted == 1) {
			return "Image uploaded Sccessfully";
		}
		return "image upload failed";  
	}
	

}
