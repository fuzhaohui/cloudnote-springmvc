package com.ces.cloud.note.core.service;

import java.util.List;

import com.ces.cloud.note.core.pojo.NoteBook;

/**
 * 相关描述： 云笔记－笔记本服务接口
 *
 * 文件名：NoteBookService.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午9:57:25 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午9:57:25 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
public interface NoteBookService {
	
	/**
	 * 方法描述：查找用户的笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:18:04
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:18:04
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param userid
	 * @return
	 * @throws Exception 
	 */
	public List<NoteBook> queryNoteBookByUser(String userid) throws Exception;
	
	/**
	 * 方法描述：通过ID查询笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 下午2:55:19
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 下午2:55:19
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBookId
	 * @return
	 * @throws Exception
	 */
	public NoteBook queryNoteBookById(int noteBookId) throws Exception;
	
	/**
	 * 方法描述：添加笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:08:57
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:08:57
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBook
	 * @throws Exception 
	 */
	public void addNoteBook(NoteBook noteBook) throws Exception;
	

	/**
	 * 方法描述：笔记本重命名
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午11:18:37
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午11:18:37
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBookid
	 * @param noteBookName
	 * @throws Exception 
	 */
	public void renameNoteBook(int noteBookid, String noteBookName) throws Exception;
	
	/**
	 * 方法描述：查询默认笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:13:37
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:13:37
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @return
	 * @throws Exception 
	 */
	public NoteBook queryDefaultNoteBook(String userid) throws Exception;
	

	/**
	 * 方法描述：更改默认笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午11:18:08
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午11:18:08
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBookId
	 * @throws Exception 
	 */
	public void updateDefaultNoteBook(int noteBookId, String userid) throws Exception;
	
	/**
	 * 方法描述：删除笔记本
	 *
	 * 作者： Fu Zhaohui
	 * 完成时间： 2013-1-11 上午10:14:09
	 * 维护人员： Fu Zhaohui
	 * 维护时间： 2013-1-11 上午10:14:09
	 * 维护原因: 
	 * 当前版本： v1.0 
	 * @param noteBookId
	 * @throws Exception 
	 */
	public void removeNoteBook(int noteBookId) throws Exception;

}
