package com.basic.bigdata.service;

import java.util.List;


public interface BaseService<T> {
	public void save(T t);
	
	public void update(T t);
	
	public void merge(T t);
	
	public void delete(Long id);
	
	public T get(Long id);
	
	public List<T> queryALL();
}
