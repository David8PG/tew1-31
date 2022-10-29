package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Seguidores;

public interface SeguidoresService {

	List<Seguidores> getSeguidores() throws Exception;
	List<Seguidores> getSeguidores(String email_usuario) throws Exception;
	List<Seguidores> getCandidatos(String email_usuario) throws Exception;
	void save(Seguidores s) throws EntityAlreadyExistsException;
	void update(Seguidores s) throws EntityNotFoundException;
	void delete(String email_seguidor) throws EntityNotFoundException;
	void delete1(String email_usuario, String email_seguidor) throws EntityNotFoundException;
	Seguidores findByEmail(String email_usuario) throws Exception;
	void aceptar(String email_usuario, String email_seguidor) throws EntityNotFoundException;
	List<Seguidores> getCandidatos1(String email) throws Exception;

	
}
