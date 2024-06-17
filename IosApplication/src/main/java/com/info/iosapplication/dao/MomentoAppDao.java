package com.info.iosapplication.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.info.iosapplication.request.MomentoAppRequest;
import com.info.iosapplication.request.MomentoFileRequest;
@Repository
public interface MomentoAppDao {
	
	public int insertUser(MomentoAppRequest momento); 
	
	public List<Map<String,Object>> fetchEmails();
	
	public int insertImage(MomentoFileRequest momentoFile);
	
	public List<byte[]> getAllBlobData();
	
	}
