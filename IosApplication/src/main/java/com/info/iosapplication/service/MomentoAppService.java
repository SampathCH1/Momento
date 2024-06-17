package com.info.iosapplication.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.info.iosapplication.request.MomentoAppRequest;
@Service
public interface MomentoAppService {
	
	public int insertValues(MomentoAppRequest momentoApp);
	
	public List<Map<String,Object>> getemails(String emailId, String paaword);
	
	public int insertFile(String emailId, MultipartFile file);
	
	public List<byte[]> retrievedBlobs();

}
