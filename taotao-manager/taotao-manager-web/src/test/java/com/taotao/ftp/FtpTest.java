package com.taotao.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.chainsaw.Main;
import org.junit.Test;

import com.taotao.utils.FtpUtil;

public class FtpTest {

	@Test
	public void TtpClientTest() throws Exception {

		FTPClient ftpClient = new FTPClient();
		
		 ftpClient.connect("192.168.243.128", 21);  
		 ftpClient.login("ftpuser",
		 "ftpuser"); FileInputStream input = new FileInputStream(new
		 File("D:/upload/0.7.jpg"));
		  ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		  ftpClient.setFileType(FTP.BINARY_FILE_TYPE); //
		 // ftpClient.setBufferSize(1000000000);
		  ftpClient.storeFile("hello1.jpg", input); 
		  ftpClient.logout();
		  ftpClient.disconnect();
		 

		/*FileInputStream input = new FileInputStream(new File("E:/upload/0.7.jpg"));

		boolean flag = FtpUtil.uploadFile("192.168.181.128", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images",
				"/2016/8/27", "yes.jpg", input);
		System.out.println(flag);
*/
	}


}
