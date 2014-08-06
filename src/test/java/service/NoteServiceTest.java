package service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ces.cloud.note.core.pojo.Note;
import com.ces.cloud.note.core.service.NoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/app_*.xml")
public class NoteServiceTest {

	@Resource
	private NoteService noteService;

	@Test
	public void testQueryNoteByAuthor() {
		try {
			System.out.println(noteService.queryNoteByAuthor("ces").size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryAuthorLastNote() {
		try {
			System.out.println(noteService.queryAuthorLastNote("ces").getNotes_title());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteByNoteBook() {
		try {
			System.out.println(noteService.queryNoteByNoteBook(39));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryDeleteNote() {
		try {
			System.out.println(noteService.queryDeleteNote("ces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchNotes() {
		try {
			System.out.println(noteService.searchNotes("a", "ces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteMaxVersion() {
		try {
			System.out.println(noteService.queryNoteMaxVersionById(12));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteVersions() {
		try {
			System.out.println(noteService.queryNoteVersionsById(2).size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteNoteVersion() {
		try {
			noteService.deleteNoteVersion(2, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteMaxNoteId() {
		try {
			System.out.println(noteService.queryNoteMaxNoteId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testAdd() {
		try {
			Note note = new Note();
			note.setNotes_id(8);
			note.setVersion(1);
			note.setNotebook_id(3);
			note.setNotes_title("测试笔记标题");
			note.setAuthor("ces");
			note.setLast_author("ces");
			note.setContent("今天部门同事分来一bug：新浪微博授权页面mobile版的错误提示黄签不能关闭，查找其原因，发现是黄签关闭后又被显示出来了，很奇怪，但很明显是有哪个地方又触发了它的show方法。然后在js中跟踪发现黄签关闭后又触发了表单提交方法，而提交表单会触发表单校验功能，校验功能会再次显示出黄签来。");
			note.setCreate_time(new Date());
			note.setLast_modified(new Date());
			noteService.addNote(note);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			Note note = noteService.queryNoteById(2, 1);
			note.setLast_author("cescescescescesces");
			note.setNotes_title("fdasfdasfdsafsaffffffffffffffffffffffffffffffffffffff");
			note.setLast_modified(new Date());
			noteService.updateNote(note);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMoveNote() {
		try {
			noteService.moveNote(3, 1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		try {
			noteService.deleteNote(45);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testRemove() {
		try {
			noteService.removeNote(45);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveAll() {
		try {
			noteService.removeAllNotes("ces");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRestAll() {
		try {
			noteService.restAllNotes("ces", 46);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
