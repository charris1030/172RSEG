package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ImageController.class);

    @RequestMapping(value="/uploadImage", method = RequestMethod.POST)
    public @ResponseBody
	String uploadFileHandler(@RequestParam("image") String image) throws Exception, IOException, SQLException {

		if (!image.isEmpty()) {
		 	Class.forName("com.mysql.jdbc.Driver");
		    Connection conn = DriverManager.getConnection("jdbc:mysql://rseg176.c1i16ovnxqwt.us-east-2.rds.amazonaws.com:3306/rseg_172", "admin", "172RSEG1030");
		    String INSERT_PICTURE = "insert into stored_images(image) values (?)";

		    FileInputStream fis = null;
		    PreparedStatement ps = null;
			try {
					byte[] bytes = image.getBytes();			
				      conn.setAutoCommit(false);
				      File file = new File(image);
				      fis = new FileInputStream(image);
				      ps = conn.prepareStatement(INSERT_PICTURE);
				      ps.setBinaryStream(2, fis, (int) file.length());
				      ps.executeUpdate();
				      conn.commit();
				    } finally {
				      ps.close();
				      fis.close();
				    }
				return "You successfully uploaded your image";
				}
				 else {
					 return "Image upload was unsuccessful";
				 }
		}
	}

    
   // public String index() {
    	//TODO: Add logic to read in image passed as parameter and leverage 
    	// provider class to post to DB
    //    return "Image Uploaded";
   // }	

//}
