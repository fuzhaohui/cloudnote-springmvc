package com.ces.cloud.note.core.pojo;

import java.util.Date;

public class Note {
	
	private int notes_id;
	private int version;
	private int notebook_id;
	private int current_version;
	private String notes_title;
	private String source;
	private String author;
	private String last_author;
	private Date create_time;
	private Date last_modified;
	private String content;
	private int status;
	private int tenant;
	private int lastEditFlag;
	public int getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getNotebook_id() {
		return notebook_id;
	}
	public void setNotebook_id(int notebook_id) {
		this.notebook_id = notebook_id;
	}
	public int getCurrent_version() {
		return current_version;
	}
	public void setCurrent_version(int current_version) {
		this.current_version = current_version;
	}
	public String getNotes_title() {
		return notes_title;
	}
	public void setNotes_title(String notes_title) {
		this.notes_title = notes_title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLast_author() {
		return last_author;
	}
	public void setLast_author(String last_author) {
		this.last_author = last_author;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTenant() {
		return tenant;
	}
	public void setTenant(int tenant) {
		this.tenant = tenant;
	}
	public int getLastEditFlag() {
		return lastEditFlag;
	}
	public void setLastEditFlag(int lastEditFlag) {
		this.lastEditFlag = lastEditFlag;
	}
}
