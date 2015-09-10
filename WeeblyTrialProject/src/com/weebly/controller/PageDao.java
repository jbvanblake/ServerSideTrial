package com.weebly.controller;

public interface PageDao {
	public void addPage(Page page);
	public void deletePage(Long id);
	public void updatePage(Page page);
}
