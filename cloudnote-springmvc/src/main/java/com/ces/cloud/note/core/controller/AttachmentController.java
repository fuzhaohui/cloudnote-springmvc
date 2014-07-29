package com.ces.cloud.note.core.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ces.cloud.note.base.util.EncryptUtils;

/**
 * 相关描述：
 *
 * 文件名：DownloadAction.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-18 下午2:23:18 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-18 下午2:23:18 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	
	
	@RequestMapping("download/{noteId}/{fileName}")  
	public void download(HttpServletResponse response, HttpServletRequest request, @PathVariable String noteId, @PathVariable String fileName) {
		String username = (String) request.getSession().getAttribute("userName");
		if(username != null && !username.equals("")) {
			String trim = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			String downloadFineName = EncryptUtils.encryptMD5(fileName.substring(0, fileName.lastIndexOf("."))) + trim;
			String userAttachmentPathStr = request.getSession().getServletContext().getRealPath("/") + "/"+ username + "/" + noteId + "/";
			
			 response.reset();  
			 response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
			 response.setContentType("application/octet-stream; charset=utf-8");  
			
			 BufferedInputStream bis = null;    
			 BufferedOutputStream bos = null;    
			 try {  
	            bis = new BufferedInputStream(new FileInputStream(userAttachmentPathStr + downloadFineName));    
	            bos = new BufferedOutputStream(response.getOutputStream());    
	            byte[] buff = new byte[2048];    
	            int bytesRead;    
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {    
	                bos.write(buff, 0, bytesRead);    
	            }    
	            bis.close();    
	            bos.close(); 
			 } catch (IOException e) {  
	            e.printStackTrace();  
			 }  
		} 
	}
}
