package com.weebly.controller;

import java.util.List;

public interface PageDao {
	public void addPage(Page page);
	public void deletePage(Long id);
	public void updatePage(Page page);
	public Page get(Long id);
	public List<Page> getAll();
}
