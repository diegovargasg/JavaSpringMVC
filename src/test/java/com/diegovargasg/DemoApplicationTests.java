package com.diegovargasg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.diegovargasg.dao.ContactDao;
import com.diegovargasg.dao.ContactDaoImpl;
import com.diegovargasg.model.Contact;

class DemoApplicationTests {
	
	private DriverManagerDataSource dataSource;
	private ContactDao dao;
	
	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contacts");
		dataSource.setUsername("userdb");
		dataSource.setPassword("password");
		
		dao = new ContactDaoImpl(dataSource);
	}
	
	@Test
	void testSave() {
		Contact contact = new Contact("Diego Vargas", "diego@gmail.com", "Königsteiner 59", "1234567890");
		int result = dao.save(contact);
		assertTrue(result > 0);
	}
	
	@Test
	void testUpdate() {
		Contact contact = new Contact(1, "Diego Gonzalez", "diego@gmail.com", "Königsteiner 59", "1234567890");
		int result = dao.update(contact);
		assertTrue(result > 0);
	}
	
	@Test
	void testGet() {
		int id = 1;
		Contact contact = dao.get(id);
		if(contact != null) {
			System.out.println(contact);
		}
		assertNotNull(contact);
	}
	
	@Test
	void testDelete() {
		int id = 1;
		int result = dao.delete(id);
		assertTrue(result > 0);
	}
	
	@Test
	void testList() {
		List<Contact> listContacts = dao.list();
		
		for(Contact contact: listContacts) {
			System.out.println(contact);
		}
		assertTrue(!listContacts.isEmpty());
	}
}
