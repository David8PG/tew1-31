package com.tew.persistence;

import java.util.List;

import com.tew.model.Seguidores;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface SeguidoresDao {
	
	List<Seguidores> getSeguidores();
	List<Seguidores> getSeguidores(String email_usuario);
	List<Seguidores> getCandidatos(String email_usuario);
	void save(Seguidores s) throws AlreadyPersistedException;
	void delete(String email_seguidor) throws Exception;
	Seguidores findByEmail(String email_usuario);
	void aceptar(String email_usuario, String email_seguidor) throws NotPersistedException;
	void delete1(String email_usuario, String email_seguidor) throws Exception;
	List<Seguidores> getCandidatos1(String email_seguidor);
	
	List<Seguidores> getSeguidores12(String email_usuario);
	void save12(String s, String l) throws AlreadyPersistedException;
}
