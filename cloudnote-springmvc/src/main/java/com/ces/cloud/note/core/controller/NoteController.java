package com.ces.cloud.note.core.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ces.cloud.note.base.util.EncryptUtils;
import com.ces.cloud.note.base.util.GenerateImage;
import com.ces.cloud.note.core.manager.NoteBookManage;
import com.ces.cloud.note.core.manager.NoteManage;
import com.ces.cloud.note.core.pojo.Note;

/**
 * 相关描述： 笔记Action类
 *
 * 文件名：NoteAction.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午11:45:18 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午11:45:18 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {
	
	@Resource
	private NoteManage noteManage;
	@Resource
	private NoteBookManage noteBookManage;
	

	/**
	 * 方法描述：　用户最近修改笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:06:15
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:06:15
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value="authorLastNote", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public Object authorLastNote() {
		String username = "ces";  //(String) getSession().getAttribute("userName");
		if(username != null && !username.equals("")) {
			return noteManage.queryLastAuthorNote(username);
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	} 
	
	/**
	 * 方法描述：　保存笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:06:02
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:06:02
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "addNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String addNote(HttpServletRequest request, @RequestParam(value = "note", required = true) Note note) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			if(note.getNotebook_id() == 0 ) {
				note.setNotebook_id(noteBookManage.queryDefaultNoteBook("ces").getNotebook_id());
			}
			if(note.getNotes_id() == 0) {
				note.setNotes_id(noteManage.queryNoteMaxNoteId() + 1);
			}
			String noteTitle = note.getNotes_title();
			if(noteTitle != null && !noteTitle.trim().equals("")) {
				note.setNotes_title(noteTitle);
			} else {
				return "{\"success\": false, \"msg\": \"笔记Title数据异常!\"}";
			}
			String content = note.getContent();
			if(content != null && !content.trim().equals("")) {
				note.setContent(content);
			}
			String source = note.getSource();
			if(source != null && !source.trim().equals("")) {
				note.setSource(source);
			}
			Date now = new Date();
			note.setCreate_time(now);
			note.setAuthor(username);
			note.setLast_author(username);
			int version = noteManage.queryNoteMaxVersionById(note.getNotes_id());
			note.setVersion(version + 1);
			note.setLast_modified(now);
			note.setCurrent_version(version + 1);
			String callBackStr = noteManage.addNote(note);
			return callBackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：　恢复笔记版本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:06:02
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:06:02
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "restNoteVersion", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String restNoteVersion(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("userName");
		if(username != null && !username.equals("")) {
			String noteId = request.getParameter("noteId");
			String version = request.getParameter("version");
			if(noteId == null || noteId.equals("") || version == null || version.equals("")) {
				return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
			} else {
				Note versionNote = noteManage.queryNoteById(Integer.parseInt(noteId), Integer.parseInt(version));
				int maxVersion = noteManage.queryNoteMaxVersionById(Integer.parseInt(noteId));
				versionNote.setVersion(maxVersion + 1);
				versionNote.setCurrent_version(maxVersion + 1);
				versionNote.setLast_modified(new Date());
				versionNote.setLast_author(username);
				String callBackStr = noteManage.addNote(versionNote);
				return callBackStr;
			}
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：　查看笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:54
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:54
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "detailNote", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public Object detailNote(HttpServletRequest request) {
		
		String noteId = request.getParameter("noteId");
		String version = request.getParameter("version");
		if(noteId == null || noteId.equals("") || version == null || version.equals("")) {
			return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
		} else {
			Note note = noteManage.queryNoteById(Integer.parseInt(noteId), Integer.parseInt(version));
			List<Note> noteVersions = noteManage.queryNoteVersions(Integer.parseInt(noteId));
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("note", note);
			resultMap.put("noteVersions", noteVersions);
			return resultMap;
		}
	}
	
	/**
	 * 方法描述：　定时保存更新笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:41
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:41
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "editNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String editNote(HttpServletRequest request, @RequestParam(value = "note", required = true) Note note) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			if(note.getNotes_id() != 0 && note.getVersion() != 0) {
				Note oldNote = noteManage.queryNoteById(note.getNotes_id(), note.getVersion());
				String noteTitle = note.getNotes_title();
				if(noteTitle != null && !noteTitle.trim().equals("")) {
					oldNote.setNotes_title(noteTitle);
				} 
				String content = note.getContent();
				if(content != null && !content.trim().equals("")) {
					oldNote.setContent(content);
				}
				String source = note.getSource();
				if(source != null && !source.trim().equals("")) {
					oldNote.setSource(source);
				}
				oldNote.setLast_author(username);
				oldNote.setLast_modified(new Date());
				String callBackStr = noteManage.editNote(oldNote);
				return callBackStr;
			} else {
				return "{\"success\": false, \"msg\": \"NoteId为空!\"}";
			}
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：移动笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:33
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:33
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "moveNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String moveNote(HttpServletRequest request) {
		String newNoteBookId = request.getParameter("newNoteBookId");
		String oldNoteBookId = request.getParameter("oldNoteBookId");
		String noteId = request.getParameter("noteId");
		if(newNoteBookId == null || newNoteBookId.equals("") || oldNoteBookId == null || oldNoteBookId.equals("") || noteId == null) {
			return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
		} else {
			String callBackStr = noteManage.moveNote(Integer.parseInt(newNoteBookId), Integer.parseInt(oldNoteBookId), Integer.parseInt(noteId));
			return callBackStr;
		}
	}
	
	/**
	 * 方法描述：移动笔记本里所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:20
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:20
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "moveAllNotes", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String moveAllNotes(HttpServletRequest request) {
		String newNoteBookId = request.getParameter("newNoteBookId");
		String oldNoteBookId = request.getParameter("oldNoteBookId");
		if(newNoteBookId == null || newNoteBookId.equals("") || oldNoteBookId == null || oldNoteBookId.equals("")) {
			return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
		} else {
			String callBackStr = noteManage.moveAllNotes(Integer.parseInt(newNoteBookId), Integer.parseInt(oldNoteBookId));
			return callBackStr;
		}
	}
	
	/**
	 * 方法描述：删除笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:13
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:13
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "deleteNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String deleteNote(HttpServletRequest request) {
		String noteId = request.getParameter("noteId");
		if(noteId == null || noteId.equals("")) {
			return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
		} else {
			String callBackStr = noteManage.deleteNote(Integer.parseInt(noteId));
			return callBackStr;
		}
	}
	
	/**
	 * 方法描述：删除笔记版本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-23 上午10:22:48
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-23 上午10:22:48
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 *//*
	public String deleteNoteVersion() {
		String noteId = request.getParameter("noteId");
		String version = request.getParameter("version");
		if(noteId == null || noteId.trim().equals("") || version == null || version.trim().equals("")) {
			return "{\"success\": false,\"msg\": \"传入数据异常!\"}");
		} else {
			String callBackStr = noteManage.deleteNoteVersion(Integer.parseInt(noteId), Integer.parseInt(version));
			return callBackStr);
		}
		return "JsonData";
	}*/
	
	/**
	 * 方法描述：恢复笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:05:03
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:05:03
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "restNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String restNote(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("userName");
		if(username != null && !username.equals("")) {
			String noteId = request.getParameter("noteId");
			if(noteId == null || noteId.equals("")) {
				return "{\"success\": false,\"msg\": \"传入数据异常!\"}";
			} else {
				String callBackStr = noteManage.restNote(username, Integer.parseInt(noteId));
				return callBackStr;
			}
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：恢复用户所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:04:53
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:04:53
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "restAllNotes", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String restAllNotes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			String callBackStr = noteManage.restAllNotes(username);
			return callBackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：　清空笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:04:19
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:04:19
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "removeNote", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String removeNote(HttpServletRequest request, @RequestParam(value="noteId", required = true)String noteId) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			String callBackStr = noteManage.removeNote(Integer.parseInt(noteId));
			// 删除上传附件
			String userAttachmentPathStr = session.getServletContext().getRealPath("/") + username;
        	File userAttachmentPath = new File(userAttachmentPathStr);
        	File userNoteAttachmentPath = new File(userAttachmentPath, noteId);
        	if(userNoteAttachmentPath.exists()) {
	        	File[] files = userNoteAttachmentPath.listFiles();
	        	for(File file : files) {
	    			file.delete();
	        	}
	        	userNoteAttachmentPath.delete();
        	}
			return callBackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：　清空用户所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午11:04:26
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午11:04:26
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "removeAllNotes", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String removeAllNotes(HttpServletRequest request, @RequestParam(value="noteId", required = true)String noteId) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			String callBackStr = noteManage.removeAllNotes(username);
			List<Note> deleteNotes = noteManage.queryDeleteNote(username);
			// 删除上传附件
			String userAttachmentPathStr = session.getServletContext().getRealPath("/")  + username;
	    	File userAttachmentPath = new File(userAttachmentPathStr);
			for(Note note: deleteNotes) {
	        	File userNoteAttachmentPath = new File(userAttachmentPath, note.getNotes_id() + "");
	        	if(userNoteAttachmentPath.exists()) {
		        	File[] files = userNoteAttachmentPath.listFiles();
		        	for(File file : files) {
		    			file.delete();
		        	}
		        	userNoteAttachmentPath.delete();
	        	}
			}
			return callBackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/**
	 * 方法描述：上传附件
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-14 上午10:58:04
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-14 上午10:58:04
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 */
	@RequestMapping(value = "uploadAttachment", method=RequestMethod.POST)
	public String uploadAttachment(HttpServletRequest request, @RequestParam(value="noteId", required = false)String noteId, @RequestParam("file") MultipartFile attachment) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
	        try {
	        	String attachmentFileName = attachment.getOriginalFilename();
	        	String imageURL = session.getServletContext().getRealPath("/") + "images";
	        	String userAttachmentPathStr = session.getServletContext().getRealPath("/") + username;
	        	File userAttachmentPath = new File(userAttachmentPathStr);
	        	if(noteId == null || noteId.equals("")) {
	        		noteId = noteManage.queryNoteMaxNoteId() + 1 + "";
	        	}
	        	File userNoteAttachmentPath = new File(userAttachmentPath, noteId);
	        	if(!userNoteAttachmentPath.exists()) {
	        		userNoteAttachmentPath.mkdirs();
	        	}
	        	File targetFile = new File(userNoteAttachmentPath,  attachmentFileName);
	        	InputStream  fileInputeStream = attachment.getInputStream();
	        	FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
	           
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = fileInputeStream.read(buffer)) > 0) {
	                fileOutputStream.write(buffer, 0, len);
	            }
	            fileOutputStream.flush();
	            fileOutputStream.close();
	            fileInputeStream.close();
	            String trim = attachmentFileName.substring(attachmentFileName.lastIndexOf(".") + 1).toLowerCase();
	            String imageName;
	            if(!trim.equals("png") && !trim.equals("jpg") && !trim.equals("gif") && !trim.equals("bmp")){
		            if(trim.equals("doc") || trim.equals("docx")) {
		            	imageName = imageURL +  "/doc.png";
		            } else if(trim.equals("ppt") || trim.equals("pptx")) {
		            	imageName = imageURL +  "/ppt.png";
		            } else if(trim.equals("xls") || trim.equals("xlsx")) {
		            	imageName = imageURL +  "/excel.png";
		            } else if(trim.equals("pdf")) {
		            	imageName = imageURL +  "/pdf.png";
		            } else {
		            	imageName = imageURL +  "/txt.png";
		            }
		            File renameFile = new File(userNoteAttachmentPath, EncryptUtils.encryptMD5(attachmentFileName.substring(0, attachmentFileName.lastIndexOf("."))) + attachmentFileName.substring(attachmentFileName.lastIndexOf(".")));
		            if(renameFile.exists()) {
		            	renameFile.delete();
		            }
		            targetFile.renameTo(renameFile);
		            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		            String imageContent = format.format(new Date()) + ", " + renameFile.length() /1000 + "KB";
		            File targetImageFile = new File(userNoteAttachmentPath, renameFile.getName().substring(0, renameFile.getName().lastIndexOf(".")) + ".png");
		            GenerateImage.generateImage(imageURL, imageName, attachmentFileName, imageContent, targetImageFile);
		            String targetImageAbsolutePath = targetImageFile.getAbsolutePath();
		            String targetImg = targetImageAbsolutePath.substring(targetImageAbsolutePath.indexOf(username)).replace("\\", "/"); 
		            return "{\"fileName\":\"" + attachmentFileName +"\", \"img\":\"" + targetImg +"\"}";
	            } else {
	            	String targetFileAbsolutePath = targetFile.getAbsolutePath();
	            	String targetImg = targetFileAbsolutePath.substring(targetFileAbsolutePath.indexOf(username)).replace("\\", "/"); 
	            	return "{\"fileName\":\"" + attachmentFileName +"\", \"img\":\"" + targetImg +"\"}";
	            }
            } catch (Exception e) {
	        	e.printStackTrace();
	        	return "{\"success\": false, \"msg\": \"上传附件异常!\"}";
	        } 
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	/*@RequestMapping(value = "/multipartFileUpload", method = RequestMethod.POST)  
    public String upload2(MultipartHttpServletRequest request, @RequestParam("name") String name) {  
        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();  
        List<MultipartFile> files = request.getFiles("file");  
        String dir = request.getSession().getServletContext().getRealPath("/") + "upload";  
        for (int i = 0; i < files.size(); i++) {  
            if (!files.get(i).isEmpty()) {  
                try {  
                    String realName = this.copyFile(files.get(i).getInputStream(), dir, files.get(i).getOriginalFilename());  
                    UploadFile u = new UploadFile();  
                    u.setRealName(realName);  
                    u.setName(files.get(i).getOriginalFilename());  
                    uploadFiles.add(u);  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        request.setAttribute("uploadFiles", uploadFiles);  
        return "success";  
    } */
}
