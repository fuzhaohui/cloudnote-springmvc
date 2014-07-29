package com.ces.cloud.note.core.manager;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ces.cloud.note.core.pojo.Note;
import com.ces.cloud.note.core.pojo.NoteBook;
import com.ces.cloud.note.core.service.NoteBookService;
import com.ces.cloud.note.core.service.NoteService;

/**
 * 相关描述：
 *
 * 文件名：NoteManage.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 下午12:00:25 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 下午12:00:25 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Component
public class NoteManage {
	
	private Logger logger = LoggerFactory.getLogger(NoteManage.class);
	
	@Resource
	private NoteService noteService;
	@Resource
	private NoteBookService noteBookService;
	
	public List<Note> searchNotes(String searchParam, String author) {
		try {
			return noteService.searchNotes(searchParam, author);
		} catch (Exception e) {
			logger.error("搜索用户笔记异常：" + e.getCause());
			return null;
		}
	}
	
	public Note searchNotesLatestNote(String searchParam, String author) {
		try {
			return noteService.searchNotesLatestNote(searchParam, author);
		} catch (Exception e) {
			logger.error("搜索用户笔记异常：" + e.getCause());
			return null;
		}
	}
	
	
	public Note queryLastAuthorNote(String author) {
		try {
			return noteService.queryAuthorLastNote(author);
		} catch (Exception e) {
			logger.error("获取用户最近编辑笔记异常：" + e.getCause());
			return null;
		}
	}
	
	public List<Note> queryNoteByNoteBook(int noteBookId) {
		try {
			return noteService.queryNoteByNoteBook(noteBookId);
		} catch (Exception e) {
			logger.error("获取笔记本笔记异常：" + e.getCause());
			return null;
		}
	}
	
	public Note queryNoteById(int noteId, int version) {
		try {
			return noteService.queryNoteById(noteId, version);
		} catch (Exception e) {
			logger.error("获取笔记本笔记异常：" + e.getCause());
			return null;
		}
	}
	
	public int queryNoteMaxVersionById(int noteId) {
		try {
			return noteService.queryNoteMaxVersionById(noteId);
		} catch (Exception e) {
			logger.info("当前Note无最大版本：" + e.getCause());
			return 0;
		}
	}
	
	public List<Note> queryNoteVersions(int noteId) {
		try {
			return noteService.queryNoteVersionsById(noteId);
		} catch (Exception e) {
			logger.error("查询笔记版本异常：" + e.getCause());
			return null;
		}
	}
	
	public int queryNoteMaxNoteId() {
		try {
			return noteService.queryNoteMaxNoteId();
		} catch (Exception e) {
			logger.error("获取笔记笔记最大ID异常：" + e.getCause());
			return 0;
		}
	}
	
	public List<Note> queryDeleteNote(String author) {
		try {
			return noteService.queryDeleteNote(author);
		} catch (Exception e) {
			logger.error("获取用户回收站笔记异常：" + e.getCause());
			return null;
		}
	}
	
	public String addNote(Note note) {
		String resultStr;
		try {
			noteService.addNote(note);
			resultStr = "{\"success\":true, \"msg\": \"笔记数据添加成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"笔记数据添加失败!\"}";
		}
		return resultStr;
	}
	
	public String editNote(Note note) {
		String resultStr;
		try {
			noteService.updateNote(note);
			resultStr = "{\"success\":true, \"msg\": \"更新笔记数据成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"更新笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String deleteNote(int noteId) {
		String resultStr;
		try {
			noteService.deleteNote(noteId);
			resultStr = "{\"success\":true, \"msg\": \"删除笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"删除笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String deleteNoteVersion(int noteId, int version) {
		String resultStr;
		try {
			noteService.deleteNoteVersion(noteId, version);
			resultStr = "{\"success\":true, \"msg\": \"删除笔记版本成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本版本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"删除笔记版本失败!\"}";
		}
		return resultStr;
	}
	
	public String restNote(String userid, int noteId) {
		String resultStr;
		try {
			NoteBook defaultNoteBook = noteBookService.queryDefaultNoteBook(userid);
			noteService.restNote(noteId, defaultNoteBook.getNotebook_id());
			resultStr = "{\"success\":true, \"msg\": \"恢复笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"恢复笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String restAllNotes(String userId) {
		String resultStr;
		try {
			NoteBook defaultNoteBook = noteBookService.queryDefaultNoteBook(userId);
			noteService.restAllNotes(userId, defaultNoteBook.getNotebook_id());
			resultStr = "{\"success\":true, \"msg\": \"恢复笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"恢复笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String removeNote(int noteId) {
		String resultStr;
		try {
			noteService.removeNote(noteId);
			resultStr = "{\"success\":true, \"msg\": \"清空笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"清空笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String removeAllNotes(String userId) {
		String resultStr;
		try {
			noteService.removeAllNotes(userId);
			resultStr = "{\"success\":true, \"msg\": \"清空笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"清空笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String moveNote(int newNoteBookId, int oldNoteBookId, int noteId) {
		String resultStr;
		try {
			noteService.moveNote(newNoteBookId, oldNoteBookId, noteId);
			resultStr = "{\"success\":true, \"msg\": \"移动笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"移动笔记失败!\"}";
		}
		return resultStr;
	}
	
	public String moveAllNotes(int newNoteBookId, int oldNoteBookId) {
		String resultStr;
		try {
			noteService.moveAllNotes(newNoteBookId, oldNoteBookId);
			resultStr = "{\"success\":true, \"msg\": \"移动笔记成功!\"}";
		} catch (Exception e) {
			logger.error("保存笔记本数据失败：",  e.getCause());
			resultStr = "{\"success\":false, \"msg\": \"移动笔记失败!\"}";
		}
		return resultStr;
	}

}
