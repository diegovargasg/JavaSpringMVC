package com.diegovargasg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diegovargasg.dao.ContactDao;
import com.diegovargasg.model.Contact;

@Controller
public class MainController {
	
	@Autowired
	private ContactDao contactDao;
	
	@RequestMapping(value = "/",  method = RequestMethod.GET)
	public ModelAndView listContact(ModelAndView model) {
		List<Contact> listContact = contactDao.list();
		model.addObject("listContact", listContact);
		model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping(value = "/new", method= RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("contact");

		return model;
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		if(contact.getId() == null) {
			contactDao.save(contact);
		} else {
			contactDao.update(contact);
		}
	
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/edit", method= RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDao.get(id);
		
		ModelAndView model = new ModelAndView();
		model.addObject("contact", contact);
		model.setViewName("contact");
		
		return model;
	}
	
	@RequestMapping(value = "/delete", method= RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		contactDao.delete(id);
		
		return new ModelAndView("redirect:/");
	}
}
