package com.info.iosapplication.daoimpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.io.ByteArrayOutputStream;

import com.info.iosapplication.dao.MomentoAppDao;
import com.info.iosapplication.request.MomentoAppRequest;
import com.info.iosapplication.request.MomentoFileRequest;

@Repository
public class MomentoAppDaoImpl implements MomentoAppDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = LogManager.getLogger(MomentoAppDaoImpl.class);
	

	@Override
	public int insertUser(MomentoAppRequest momento) {
		try {
		log.info("entered into insertUser in MomentoAppDaoImpl");
		String insertSql = "INSERT INTO MOMENTO_USERS (USER_NAME, EMAIL_ID, PASSWORD, PHONE_NUMBER, ADDRESS) \r\n"
				+ "VALUES (?, ?, ?, ?, ?)";
      return jdbcTemplate.update(insertSql,momento.getUserName(),momento.getEmailId(),momento.getPassword(),momento.getPhoneNumber(),momento.getAddress());
		}catch(Exception e) {
			log.info("exception occured when inserting the DATA with reason : {}" ,e.getMessage());
		}
		return 0;
	}
	
	public List<Map<String,Object>> fetchEmails(){
		String sqlEmail = "SELECT * FROM MOMENTO_USERS";
		return jdbcTemplate.queryForList(sqlEmail);
	}
	
	public int insertImage(MomentoFileRequest momentoFile) {
		String sqlimage = "INSERT INTO MOMENTO_USER_IMAGES (EMAIL_ID, USER_FILES)\r\n"
				+ "VALUES (?, ?)";
		return jdbcTemplate.update(sqlimage,momentoFile.getEmailId(),momentoFile.getFile());
	}
	public List<byte[]> getAllBlobData() {
	    String sql = "SELECT USER_FILES FROM MOMENTO_USER_IMAGES";
	    return jdbcTemplate.query(sql, new BlobDataMapper());
	}

	private static class BlobDataMapper implements RowMapper<byte[]> {
	    @Override
	    public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Blob blob = rs.getBlob("USER_FILES");
	        if (blob != null) {
	            try (InputStream inputStream = blob.getBinaryStream()) {
	                return readBytesFromInputStream(inputStream);
	            } catch (SQLException | IOException e) {
	        
	                return null; // Or handle the error as needed
	            }
	        }
	        return null;  
	    }
	}
	
	private static byte[] readBytesFromInputStream(InputStream inputStream) throws IOException {
	    byte[] buffer = new byte[1024];
	    int bytesRead;
	    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        return outputStream.toByteArray();
	    }
	}
    
}
