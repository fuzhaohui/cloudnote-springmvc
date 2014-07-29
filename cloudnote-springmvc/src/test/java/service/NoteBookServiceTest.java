package service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ces.cloud.note.core.pojo.NoteBook;
import com.ces.cloud.note.core.service.NoteBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/app_*.xml")
public class NoteBookServiceTest {

	@Resource
	private NoteBookService noteBookService;

	@Test
	public void testSave() {
		try {
			NoteBook noteBook = new NoteBook();
			noteBook.setUserid("ces");
			noteBook.setNotebook_name("测试笔记本7");
			noteBook.setDefault_flag(1);
			noteBook.setCreate_time(new Date());
			noteBookService.addNoteBook(noteBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryNoteBookByUser() {
		try {
			System.out.println(noteBookService.queryNoteBookByUser("ces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryDefaultNoteBook() {
		try {
			System.out.println(noteBookService.queryDefaultNoteBook("ces"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testUpdateNoteBook() {
		try {
			noteBookService.renameNoteBook(5, "测试笔记本５");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testUpdateDefaultNoteBook() {
		try {
			noteBookService.updateDefaultNoteBook(4, "test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteNoteBook() {
		try {
			noteBookService.removeNoteBook(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
