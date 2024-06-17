package com.info.iosapplication.response;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class MomentoAppResponse {
	
	private List<Map<String,Object>> userDetailsList;
	
	private List<byte[]> uploadedImages;

}
