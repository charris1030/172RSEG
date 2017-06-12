package providers;

import java.sql.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class SaveImageToDatabase {
	
	  public static void main(String[] args) throws Exception, IOException, SQLException {
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://rseg176.c1i16ovnxqwt.us-east-2.rds.amazonaws.com:3306/rseg_172", "admin", "172RSEG1030");
	    String INSERT_PICTURE = "insert into stored_images(id, image) values (?, ?)";

	    FileInputStream fis = null;
	    PreparedStatement ps = null;
	    try {
	      conn.setAutoCommit(false);
	      File file = new File("C:/Users/Chris/Documents/image.jpg");
	      fis = new FileInputStream(file);
	      ps = conn.prepareStatement(INSERT_PICTURE);
	      ps.setString(1, "001");;
	      ps.setBinaryStream(2, fis, (int) file.length());
	      ps.executeUpdate();
	      conn.commit();
	    } finally {
	      ps.close();
	      fis.close();
	    }
	  }
	}
	