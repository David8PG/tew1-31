package com.tew.persistence;

import java.util.List;

import com.tew.model.Foto;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface FotoDao {
	
	List<Foto> getFotos();
	List<Foto> getFotos(String email_usuario);
	List<Foto> getFotosSeguidores(String email);
	void save(Foto f) throws AlreadyPersistedException;
	Foto findById(Long id);
	void delete(String email) throws NotPersistedException;
	void delete1(Foto f) throws NotPersistedException;
	
}
