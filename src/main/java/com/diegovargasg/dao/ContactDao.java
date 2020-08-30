package com.diegovargasg.dao;

import java.util.List;
import com.diegovargasg.model.Contact;

public interface ContactDao {
	public int save(Contact contact);
	
	public int update(Contact contact);
	
	public Contact get(Integer id);
	
	public int delete(Integer id);
	
	public List<Contact> list();
}
