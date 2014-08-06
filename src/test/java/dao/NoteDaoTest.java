package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ces.cloud.note.core.dao.NoteDao;
import com.ces.cloud.note.core.pojo.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/app_*.xml")
public class NoteDaoTest {

	@Resource
	private NoteDao noteDao;

	@Test
	public void testQueryNoteByAuthor() {
		try {
			System.out.println(noteDao.getEntityList("queryNoteByAuthor", "ces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteByNoteBook() {
		try {
			System.out.println(noteDao.getEntityList("queryNoteByNoteBook", 3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryDeleteNote() {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("author", "ces");
			paramMap.put("status", 0);
			System.out.println(noteDao.getEntityList("queryDeleteNote", paramMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		try {
			Note note = new Note();
			note.setNotes_id(1);
			note.setVersion(1);
			note.setNotebook_id(3);
			note.setNotes_title("测试笔记标题");
			note.setAuthor("ces");
			note.setLast_author("ces");
			note.setContent("aaaaaaaaaaaaaaaaaaaaaaaaa");
			note.setCreate_time(new Date());
			note.setLast_modified(new Date());
			System.out.println(noteDao.save("saveNote", note));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("notes_id", 1);
			paramMap.put("version", 1);
			Note note = noteDao.getEntity("queryNoteById", paramMap);
			note.setAuthor("cescescescescesces");
			note.setNotes_title("fdasfdasfdsafsaffffffffffffffffffffffffffffffffffffff");
			System.out.println(noteDao.update("updateNote", note));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMoveNote() {
		try {
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("new_notebook_id", 3);
			paramMap.put("old_notebook_id", 1);
			System.out.println(noteDao.update("moveNote", paramMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		try {
			Map<String, Integer> paramMap = new HashMap<String, Integer>();
			paramMap.put("notes_id", 1);
			paramMap.put("status", 1);
			System.out.println(noteDao.update("deleteNote", paramMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testRemove() {
		try {
			System.out.println(noteDao.remove("removeNote", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
