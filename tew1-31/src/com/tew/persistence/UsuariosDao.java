package com.tew.persistence;

import java.util.List;

import com.tew.model.Seguidores;
import com.tew.model.Usuarios;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public interface UsuariosDao {
	
	List<Usuarios> getUsuarios();
	List<Usuarios> getUsuarios(String filtro, String email);
	void save(Usuarios u) throws AlreadyPersistedException;
	void guardarSeguidor(Seguidores nuevoSeguidor) throws AlreadyPersistedException;
	void delete(String email) throws NotPersistedException;
	void reset() throws Exception;
	Usuarios findByEmail(String email);
}
