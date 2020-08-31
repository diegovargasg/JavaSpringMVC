package com.diegovargasg.controller;

import java.util.List;

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
		System.out.println(contact);
		contactDao.save(contact);
		return new ModelAndView("redirect:/");
	}
}
