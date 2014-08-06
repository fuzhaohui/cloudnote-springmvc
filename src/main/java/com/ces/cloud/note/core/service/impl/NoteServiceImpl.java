package com.ces.cloud.note.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ces.cloud.note.core.dao.NoteDao;
import com.ces.cloud.note.core.pojo.Note;
import com.ces.cloud.note.core.service.NoteService;

/**
 * 相关描述：云笔记－笔记服务实现
 *
 * 文件名：NoteServiceImpl.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午10:49:27 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午10:49:27 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,  readOnly = true)
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteDao noteDao;

	@Transactional(readOnly=true)
	@Override
	public List<Note> searchNotes(String searchParam, String author) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("searchParam", searchParam);
		paramsMap.put("author", author);
		paramsMap.put("status", 0);
		return noteDao.getEntityList("searchNote", paramsMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Note searchNotesLatestNote(String searchParam, String author) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("searchParam", searchParam);
		paramsMap.put("author", author);
		paramsMap.put("status", 0);
		return noteDao.getEntity("searchNotesLatestNote", paramsMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Note> queryNoteByAuthor(String author) throws Exception {
		return noteDao.getEntityList("queryNoteByAuthor", author);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Note queryAuthorLastNote(String author) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("author", author);
		paramMap.put("status", 0);
		return noteDao.getEntity("queryAuthorLastNote", author);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Note> queryNoteByNoteBook(int noteBookId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("notebook_id", noteBookId);
		paramMap.put("status", 0);
		return noteDao.getEntityList("queryNoteByNoteBook", paramMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Note> queryDeleteNote(String author) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("author", author);
		paramMap.put("status", 1);
		return noteDao.getEntityList("queryDeleteNote", paramMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Note queryNoteById(int noteId, int version) throws Exception {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("notes_id", noteId);
		paramMap.put("version", version);
		return noteDao.getEntity("queryNoteById", paramMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public int queryNoteMaxVersionById(int noteId) throws Exception {
		return noteDao.getEntity("queryNoteMaxVersionById", noteId);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Note> queryNoteVersionsById(int noteId) throws Exception {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("notes_id", noteId);
		paramMap.put("status", 0);
		return noteDao.getEntityList("queryNoteVersionsById", paramMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public int queryNoteMaxNoteId() throws Exception {
		return noteDao.getEntity("queryNoteMaxNoteId");
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void addNote(Note note) throws Exception {
		noteDao.save("saveNote", note);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void updateNote(Note note) throws Exception {
		noteDao.update("updateNote", note);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void moveAllNotes(int newNoteBookId, int oldNoteBookId) throws Exception{
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("new_notebook_id", newNoteBookId);
		paramMap.put("old_notebook_id", oldNoteBookId);
		noteDao.update("moveAllNotes", paramMap);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void moveNote(int newNoteBookId, int oldNoteBookId, int noteId) throws Exception{
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("new_notebook_id", newNoteBookId);
		paramMap.put("old_notebook_id", oldNoteBookId);
		paramMap.put("note_id", noteId);
		noteDao.update("moveNote", paramMap);
	}
		

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void deleteNote(int noteId) throws Exception {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("notebook_id", -1);
		paramMap.put("notes_id", noteId);
		paramMap.put("status", 1);
		noteDao.update("deleteNote", paramMap);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void deleteNoteVersion(int noteId, int version) throws Exception{
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("notes_id", noteId);
		paramMap.put("version", version);
		paramMap.put("status", 1);
		noteDao.update("deleteNoteVersion", paramMap);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void restNote(int noteId, int noteBookId) throws Exception {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("notes_id", noteId);
		paramMap.put("status", 0);
		paramMap.put("notebook_id", noteBookId);
		noteDao.update("deleteNote", paramMap);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void restAllNotes(String userId, int noteBookId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("author", userId);
		paramMap.put("status", 0);
		paramMap.put("notebook_id", noteBookId);
		paramMap.put("status2", 1);
		noteDao.update("restAllNotes", paramMap);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void removeNote(int noteId) throws Exception {
		noteDao.remove("removeNote", noteId);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void removeAllNotes(String userId) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("author", userId);
		paramMap.put("status", 1);
		noteDao.update("removeAllNotes", paramMap);
	}

}
