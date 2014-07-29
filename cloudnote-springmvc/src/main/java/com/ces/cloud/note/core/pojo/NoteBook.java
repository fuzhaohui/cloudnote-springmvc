package com.ces.cloud.note.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteBook {

	private int notebook_id;
	private String userid;
	private String notebook_name;
	private Date create_time;
	private int default_flag;
	private String note;
	private int tenant;
	private List<Note> notes = new ArrayList<Note>();
	
	public int getNotebook_id() {
		return notebook_id;
	}
	public void setNotebook_id(int notebook_id) {
		this.notebook_id = notebook_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNotebook_name() {
		return notebook_name;
	}
	public void setNotebook_name(String notebook_name) {
		this.notebook_name = notebook_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getDefault_flag() {
		return default_flag;
	}
	public void setDefault_flag(int default_flag) {
		this.default_flag = default_flag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getTenant() {
		return tenant;
	}
	public void setTenant(int tenant) {
		this.tenant = tenant;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
