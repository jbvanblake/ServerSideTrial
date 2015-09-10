package com.weebly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PageDaoImpl implements PageDao{
	
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addPage(Page page) {
		if (!mongoTemplate.collectionExists(Page.class)) {
			mongoTemplate.createCollection(Page.class);
		}
		mongoTemplate.insert(page);
		
	}

	@Override
	public void deletePage(Long id) {
		Query query = new BasicQuery("{ _id : " + id + "}");
		mongoTemplate.remove(query, Page.class);
	}

	@Override
	public void updatePage(Page page) {
		Query query = new BasicQuery("{ _id : " + page.getId() + "}");
		mongoTemplate.remove(query, Page.class);
		addPage(page);
		
	}

}
