package com.weebly.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.omg.CORBA.portable.ApplicationException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Controller
public class WeeblyTrialController {
	
	@Autowired
	private PageDao pageDao;
	
	@RequestMapping(value = "/api/pages", method = RequestMethod.POST)
	public void createPage(@ModelAttribute Page page, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		pageDao.updatePage(page);
		response.setStatus(HttpServletResponse.SC_OK);

		setCrossDomainResponseHeaders(response);
	}
	
	
	@RequestMapping(value = "/api/page/{id}", method = RequestMethod.GET)
	public @ResponseBody Page getPage(@PathVariable Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
			Page page = pageDao.get(id);
		
			
		response.setStatus(HttpServletResponse.SC_OK);

		setCrossDomainResponseHeaders(response);
		return page;
	}
	
	@RequestMapping(value = "/api/pages", method = RequestMethod.GET)
	public void getPages(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
			List<Page> pages = pageDao.getAll();
			ObjectMapper mapper = new ObjectMapper();
			
			String json="";
			try {
				json = mapper.writeValueAsString(pages);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		response.setStatus(HttpServletResponse.SC_OK);
		setCrossDomainResponseHeaders(response);
		
		
		try {
			response.getOutputStream().write(json.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value = "/api/page/{id}", method = RequestMethod.DELETE)
	public void removePage(@PathVariable Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		setCrossDomainResponseHeaders(response);
		pageDao.deletePage(id);
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	
	@RequestMapping(value = "/api/page/delete", method = RequestMethod.POST)
	public void crossDomainRemovePage(@ModelAttribute LongHolder idHolder, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		setCrossDomainResponseHeaders(response);
		pageDao.deletePage(idHolder.getId());
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	@RequestMapping(value = "/api/page/img", method = RequestMethod.POST)
	public void uploadImage(@ModelAttribute MultipartFile image, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		setCrossDomainResponseHeaders(response);
		
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	
	public void setCrossDomainResponseHeaders(HttpServletResponse response) {		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Authorization");
		
	}
	
	
}
