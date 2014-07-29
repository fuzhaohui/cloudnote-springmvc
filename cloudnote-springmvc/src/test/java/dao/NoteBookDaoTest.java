package dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ces.cloud.note.core.dao.NoteBookDao;
import com.ces.cloud.note.core.pojo.NoteBook;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring/app_*.xml")  
public class NoteBookDaoTest {
	
	@Resource
	private NoteBookDao noteBookDao;
	
	@Test
	public void testSave() {
		try {
			NoteBook noteBook = new NoteBook();
			noteBook.setUserid("test");
			noteBook.setNotebook_name("测试笔记本3");
			noteBook.setDefault_flag(1);
			noteBook.setCreate_time(new Date());
			noteBookDao.save("saveNoteBook", noteBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteBookByUser() {
		try {
			System.out.println(noteBookDao.getEntityList("queryNoteBookByUser", "test"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryDefaultNoteBook() {
		try {
			System.out.println(noteBookDao.getEntityList("queryDefaultNoteBook", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteBookById() {
		try {
			System.out.println(noteBookDao.getEntityById("queryNoteBookById", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateNoteBook() {
		try {
			NoteBook noteBook = noteBookDao.getEntityById("queryNoteBookById", 1);
			noteBook.setDefault_flag(0);
			noteBookDao.update("updateNoteBook", noteBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteNoteBook() {
		try {
			System.out.println(noteBookDao.remove("removeNotebook", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
