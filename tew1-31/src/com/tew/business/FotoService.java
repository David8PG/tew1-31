package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Foto;

public interface FotoService {
	
	List<Foto> getFotos() throws Exception;
	List<Foto> getFotos(String email) throws Exception;
	List<Foto> getFotosSeguidores(String email);
	void save(Foto f) throws EntityAlreadyExistsException;
	void update(Foto f) throws EntityNotFoundException;
	void delete(String email) throws EntityNotFoundException, Exception;
	Foto findById(Long id) throws Exception;
	void delete1(Foto f) throws Exception;
}
