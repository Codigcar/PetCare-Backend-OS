package com.pe.edu.upc.petcare.service;

import java.util.List;

public interface CrudService<T>{

	public T save(T t) throws Exception;
	public T update(T t) throws Exception;
	public T findById(Long id) throws Exception;
	public List<T> findAll() throws Exception;
	public T deleteById(Long id) throws Exception;
}
