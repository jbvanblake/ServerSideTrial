package com.trial.controller;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Page {

   @Id
    private Long id;

    private String pageName;
    private String html;

    public Page() {}

    public Page(String pageName, String html) {
        this.pageName = pageName;
        this.html = html;
    }

    @Override
    public String toString() {
        return String.format(
                "Page[id=%s, pageName='%s', html='%s']",
                id, pageName, html);
    }

	public Long getId() {
		return id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
