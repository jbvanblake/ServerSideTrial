package com.weebly.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Controller
public class WeeblyTrialController {
	
	@Autowired
	private PageDao pageDao;
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public void createPage(@ModelAttribute Page page, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if(page.getId() != null) {
			pageDao.updatePage(page);
		} else {
			pageDao.addPage(page);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value = "/page/{id}", method = RequestMethod.DELETE)
	public void removePage(@PathVariable Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		pageDao.deletePage(id);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
