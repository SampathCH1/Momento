package com.info.iosapplication.request;

import lombok.Data;

@Data
public class MomentoFileRequest {
	
	private String emailId;
	
	private byte[] file;

}
