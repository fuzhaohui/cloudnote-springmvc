package com.ces.cloud.note.core.service;

import java.util.List;

import com.ces.cloud.note.core.pojo.Note;

/**
 * 相关描述：云笔记－笔记服务接口
 *
 * 文件名：NoteService.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午10:14:54 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午10:14:54 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
public interface NoteService {
	
	/**
	 * 方法描述：搜索Note
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-16 下午4:57:32
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-16 下午4:57:32
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param searchParam
	 * @param author
	 * @return
	 * @throws Exception
	 */
	public List<Note> searchNotes(String searchParam, String author) throws Exception;
	
	/**
	 * 方法描述：搜索Note
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-16 下午4:57:32
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-16 下午4:57:32
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param searchParam
	 * @param author
	 * @return
	 * @throws Exception
	 */
	public Note  searchNotesLatestNote(String searchParam, String author) throws Exception;
	
	/**
	 * 方法描述：查询用户的所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:28:54
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:28:54
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param author
	 * @return
	 * @throws Exception 
	 */
	public List<Note> queryNoteByAuthor(String author) throws Exception;
	
	/**
	 * 方法描述：查询用户最近编辑的一条笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 下午2:35:48
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 下午2:35:48
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param author
	 * @return
	 * @throws Exception
	 */
	public Note  queryAuthorLastNote(String author) throws Exception;
	
	
	/**
	 * 方法描述：查询笔记本里的所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:29:14
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:29:14
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBookId
	 * @return
	 * @throws Exception 
	 */
	public List<Note> queryNoteByNoteBook(int noteBookId) throws Exception;
	
	/**
	 * 方法描述：查询用户删除的笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:37:36
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:37:36
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param author
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public List<Note> queryDeleteNote(String author) throws Exception;
	
	/**
	 * 方法描述：查看笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:29:48
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:29:48
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @param version
	 * @return
	 * @throws Exception 
	 */
	public Note queryNoteById(int noteId, int version) throws Exception;
	
	/**
	 * 方法描述：查询笔记最大版本号
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-15 下午5:58:02
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-15 下午5:58:02
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @return
	 * @throws Exception
	 */
	public int queryNoteMaxVersionById(int noteId) throws Exception;
	
	/**
	 * 方法描述：查询笔记的所有版本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-23 上午9:44:26
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-23 上午9:44:26
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @return
	 * @throws Exception
	 */
	public List<Note> queryNoteVersionsById(int noteId) throws Exception;
	
	/**
	 * 方法描述： 查询Note表里最大ID
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-16 上午9:30:03
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-16 上午9:30:03
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 * @throws Exception
	 */
	public int queryNoteMaxNoteId() throws Exception;
	
	/**
	 * 方法描述：添加笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:30:06
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:30:06
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param note
	 * @throws Exception 
	 */
	public void addNote(Note note) throws Exception;
	
	/**
	 * 方法描述：修改笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:30:16
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:30:16
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param note
	 * @throws Exception 
	 */
	public void updateNote(Note note) throws Exception;
	
	/**
	 * 方法描述：移动笔记本里笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午11:04:05
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午11:04:05
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param newNoteBookId
	 * @param oldNoteBookId
	 * @throws Exception
	 */
	public void moveNote(int newNoteBookId, int oldNoteBookId, int noteId) throws Exception;
	
	/**
	 * 方法描述：
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 下午4:49:41
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 下午4:49:41
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param newNoteBookId
	 * @param oldNoteBookId
	 * @throws Exception
	 */
	public void moveAllNotes(int newNoteBookId, int oldNoteBookId) throws Exception;
	
	/**
	 * 方法描述：删除笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:30:27
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:30:27
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @throws Exception 
	 */
	public void deleteNote(int noteId) throws Exception;
	
	/**
	 * 方法描述：删除笔记版本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-23 上午9:59:13
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-23 上午9:59:13
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @param version
	 * @throws Exception
	 */
	public void deleteNoteVersion(int noteId, int version) throws Exception;
	
	/**
	 * 方法描述：恢复笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:30:40
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:30:40
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @throws Exception 
	 */
	public void restNote(int noteId, int noteBookId) throws Exception;
	
	/**
	 * 方法描述： 恢复用户所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 下午5:00:56
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 下午5:00:56
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param userId
	 * @throws Exception
	 */
	public void restAllNotes(String userId, int noteBookId) throws Exception;
	
	/**
	 * 方法描述：清空笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:30:55
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:30:55
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteId
	 * @throws Exception 
	 */
	public void removeNote(int noteId) throws Exception;
	
	/**
	 * 方法描述： 清空用户所有笔记
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 下午5:01:42
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 下午5:01:42
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param userId
	 * @throws Exception
	 */
	public void removeAllNotes(String userId) throws Exception;
	

}
