package com.ces.cloud.note.core.manager;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ces.cloud.note.core.pojo.NoteBook;
import com.ces.cloud.note.core.service.NoteBookService;

/**
 * 相关描述：
 *
 * 文件名：NoteBookManage.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 下午12:00:00 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 下午12:00:00 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Component
public class NoteBookManage {
	
	private Logger logger = LoggerFactory.getLogger(NoteBookManage.class);
	
	@Resource
	private NoteBookService noteBookService;
	
	public List<NoteBook> queryNoteBookByUser(String userid) {
		try {
			return noteBookService.queryNoteBookByUser(userid);
		} catch (Exception e) {
			logger.error("显示用户笔记列表异常：", e.getCause());
			return null;
		}
	}
	
	public NoteBook queryDefaultNoteBook(String userid) {
		try {
			return noteBookService.queryDefaultNoteBook(userid);
		} catch (Exception e) {
			logger.error("显示用户笔记列表异常：", e.getCause());
			return null;
		}
	}
	
	public String addNoteBook(NoteBook noteBook) {
		String resultStr;
		try {
			NoteBook defaultNoteBook = noteBookService.queryDefaultNoteBook(noteBook.getUserid());
			if(defaultNoteBook == null) {
				noteBook.setDefault_flag(1);
			}
			noteBookService.addNoteBook(noteBook);
			resultStr = "{\"success\":true, \"msg\": \"笔记本数据添加成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"笔记本数据添加失败!\"}";
		}
		return resultStr;
	}
	
	public String setDefaultNoteBook(int noteBookId, String userid) {
		String resultStr;
		try {
			noteBookService.updateDefaultNoteBook(noteBookId, userid);
			resultStr = "{\"success\":true, \"msg\": \"设置默认笔记本成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"设置默认笔记本失败!\"}";
		}
		return resultStr;
	}

	public String renameNoteBook(int noteBookId, String newNoteBookName) {
		String resultStr;
		try {
			noteBookService.renameNoteBook(noteBookId, newNoteBookName);
			resultStr = "{\"success\":true, \"msg\": \"重命名笔记本成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"重命名笔记本失败!\"}";
		}
		return resultStr;
	}
	
	public String removeNoteBook(int noteBookId) {
		String resultStr;
		try {
			noteBookService.removeNoteBook(noteBookId);
			resultStr = "{\"success\":true, \"msg\": \"删除笔记本成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"删除笔记本失败!\"}";
		}
		return resultStr;
	}
}
