package com.taotao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;

	@Value("${FTP_PORT}")
	private Integer FTP_PORT;

	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;

	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@SuppressWarnings("unchecked")
	@Override
	public Map pictureUpload(MultipartFile file) {
		Map resultMap = new HashMap<>();
		try {
			
			String oldName = file.getOriginalFilename();
			String newName = IDUtils.genImageName() + oldName;
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			System.out.println(file.getSize());
			boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
					filePath, newName, file.getInputStream());
			if(!flag) {
				resultMap.put("error", 1);
				resultMap.put("message", "上传失败！");
			} else {
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + filePath +"/" + newName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "上传异常！");
		}
		
		return resultMap;
	}

}
