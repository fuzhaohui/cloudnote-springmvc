package com.ces.cloud.note.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ces.cloud.note.core.manager.NoteBookManage;
import com.ces.cloud.note.core.manager.NoteManage;
import com.ces.cloud.note.core.pojo.Note;
import com.ces.cloud.note.core.pojo.NoteBook;

/**
 * 相关描述： 笔记本Action类
 *
 * 文件名：NoteBookAction.java
 * 作者： Fu Zhaohui 
 * 完成时间：2013-1-11 上午11:43:56 
 * 维护人员：Fu Zhaohui  
 * 维护时间：2013-1-11 上午11:43:56 
 * 维护原因：  
 * 当前版本： v1.0 
 *
 */
@Controller  
@RequestMapping("/notebook") 
public class NoteBookController {
	
	@Resource
	private NoteBookManage noteBookManage;
	@Resource
	private NoteManage noteManage;
	
	@RequestMapping(method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public Object listUserNoteBook() {
		String username ="ces";// (String) getSession().getAttribute("userName");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(username != null && !username.equals("")) {
			Note lastEditNote = noteManage.queryLastAuthorNote(username);
			resultMap.put("lastEditNote", lastEditNote);
			List<NoteBook> noteBooks = noteBookManage.queryNoteBookByUser(username);
			boolean lastEditFlag = false;
			for(NoteBook noteBook : noteBooks) {
				
				List<Note> listNote =  noteManage.queryNoteByNoteBook(noteBook.getNotebook_id());
				if(!lastEditFlag) {
					for(int i = 0; i < listNote.size(); i++) {
						if(listNote.get(i).getNotes_id() == lastEditNote.getNotes_id()) {
							listNote.get(i).setLastEditFlag(1);
							lastEditFlag = true;
							break;
						}
					}
				}
				noteBook.getNotes().addAll(listNote);
			}
			NoteBook recycleNoteBook = new NoteBook();
			recycleNoteBook.setNotebook_id(-1);
			recycleNoteBook.setNotebook_name("回收站");
			List<Note> deleteNoteList = noteManage.queryDeleteNote(username);
			recycleNoteBook.getNotes().addAll(deleteNoteList);
			noteBooks.add(recycleNoteBook);
			resultMap.put("noteBookList", noteBooks);
			return resultMap;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String addNoteBook(HttpSession session, @RequestParam(value="noteBook", required=true)NoteBook noteBook){
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			noteBook.setUserid(username);
			String noteBookName = noteBook.getNotebook_name();
			if(noteBookName != null && !noteBookName.trim().equals("")) {
				noteBook.setNotebook_name(noteBookName);
			} else {
				return "{\"success\": false, \"msg\": \"笔记本名数据异常!\"}";
			}
			noteBook.setCreate_time(new Date());
			String callBackStr = noteBookManage.addNoteBook(noteBook);
			return callBackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public Object searchNotes(HttpSession session, @RequestParam(value="searchParam", required=false, defaultValue="")String searchParam) {
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			List<NoteBook> noteBooks = noteBookManage.queryNoteBookByUser(username);
			Map<Integer, NoteBook> noteBookMap = new HashMap<Integer, NoteBook>();
			for(NoteBook noteBook : noteBooks) {
				noteBookMap.put(noteBook.getNotebook_id(), noteBook);
			}
			if(searchParam != null && !searchParam.trim().equals("")) {
				List<NoteBook> searchNoteBookList = new ArrayList<NoteBook>();
				Note lastEditNote = noteManage.searchNotesLatestNote(searchParam, username);
				List<Note> searchNoteList = noteManage.searchNotes(searchParam, username);
				for(int i = 0; i < searchNoteList.size(); i++) {
					if(noteBookMap.get(searchNoteList.get(i).getNotebook_id()) != null) {
						noteBookMap.get(searchNoteList.get(i).getNotebook_id()).getNotes().add(searchNoteList.get(i));
					}
					if(searchNoteList.get(i).getNotes_id() == lastEditNote.getNotes_id()) {
						searchNoteList.get(i).setLastEditFlag(1);
					}
				}
				searchNoteBookList.addAll(noteBookMap.values());
				for(int j = searchNoteBookList.size() -1 ; j >= 0; j--) {
					System.out.println(searchNoteBookList.get(j).getNotes() + ": " + searchNoteBookList.get(j).getNotebook_id());
					if(searchNoteBookList.get(j).getNotes() == null || searchNoteBookList.get(j).getNotes().size() == 0) {
						searchNoteBookList.remove(j);
					}
				}
				return searchNoteBookList;
			}  else {
				return listUserNoteBook();
			}
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	@RequestMapping(value="setDefault", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String setDefaultNoteBook(HttpSession session, @RequestParam(value="noteBookId", required=true)String noteBookId){
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			String callbackStr = noteBookManage.setDefaultNoteBook(Integer.parseInt(noteBookId), username);
			return callbackStr;
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
	
	@RequestMapping(value="rename", method=RequestMethod.GET, produces = {"application/json;charset=UTF-8"}, headers="Accept=application/json")
	@ResponseBody
	public String renameNoteBook(@RequestParam(value="noteBookId", required=true)String noteBookId, @RequestParam(value="newNoteBookName", required=true)String newNoteBookName) {
		String callbackStr = noteBookManage.renameNoteBook(Integer.parseInt(noteBookId), newNoteBookName);
		return callbackStr;
	}
	
	public String removeNoteBook(HttpSession session, @RequestParam(value="noteBookId", required=true)String noteBookId) {
		String username = (String) session.getAttribute("userName");
		if(username != null && !username.equals("")) {
			int  defaultNoteBookId = noteBookManage.queryDefaultNoteBook(username).getNotebook_id();
			if(defaultNoteBookId == Integer.parseInt(noteBookId)) {
				return "{\"success\": false, \"msg\": \"默认笔记本不能删除!\"}";
			} else {
				noteManage.moveAllNotes(defaultNoteBookId, Integer.parseInt(noteBookId));
				String callbackStr = noteBookManage.removeNoteBook(Integer.parseInt(noteBookId));
				return callbackStr;
			}
		} else {
			return "{\"success\": false, \"msg\": \"当前用户为空!\"}";
		}
	}
}
