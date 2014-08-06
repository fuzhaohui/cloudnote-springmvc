package com.ces.cloud.note.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.cloud.note.core.dao.NoteBookDao;
import com.ces.cloud.note.core.pojo.NoteBook;
import com.ces.cloud.note.core.service.NoteBookService;

/**
 * 相关描述：云笔记－笔记本服务实现
 *
 * 文件名：NoteBookServiceImpl.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午10:50:08 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午10:50:08 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Service
public class NoteBookServiceImpl implements NoteBookService {
	
	@Autowired
	private NoteBookDao noteBookDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<NoteBook> queryNoteBookByUser(String userid) throws Exception {
		return noteBookDao.getEntityList("queryNoteBookByUser", userid);
	}

	@Transactional(readOnly=true)
	@Override
	public void addNoteBook(NoteBook noteBook) throws Exception {
		noteBookDao.save("saveNoteBook", noteBook);
	}
	
	@Transactional(readOnly=true)
	@Override
	public NoteBook queryDefaultNoteBook(String userid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("default_flag", 1);
		return noteBookDao.getEntity("queryDefaultNoteBook", paramMap);
	}
	
	@Transactional(readOnly=true)
	@Override
	public NoteBook queryNoteBookById(int noteBookId) throws Exception {
		return noteBookDao.getEntityById("queryNoteBookById", noteBookId);
	}
	
	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void renameNoteBook(int noteBookId, String noteBookName) throws Exception {
		NoteBook noteBook = noteBookDao.getEntityById("queryNoteBookById", noteBookId);
		noteBook.setNotebook_name(noteBookName);
		noteBookDao.update("updateNoteBook", noteBook);
	}


	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void updateDefaultNoteBook(int noteBookId, String userid) throws Exception {
		NoteBook defaultNoteBook = queryDefaultNoteBook(userid);
		if(defaultNoteBook != null) {
			defaultNoteBook.setDefault_flag(0);
			noteBookDao.update("updateNoteBook", defaultNoteBook);
		}
		NoteBook newDefaultNoteBook = noteBookDao.getEntityById("queryNoteBookById", noteBookId);
		newDefaultNoteBook.setDefault_flag(1);
		noteBookDao.update("updateNoteBook", newDefaultNoteBook);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void removeNoteBook(int noteBookId) throws Exception {
		noteBookDao.remove("removeNotebook", noteBookId);
	}

}
