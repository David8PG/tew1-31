package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Seguidores;
import com.tew.model.Usuarios;

public interface UsuariosService {
	List<Usuarios> getUsuarios() throws Exception;
	List<Usuarios> getUsuarios(String filtro, String email);
	void save(Usuarios u) throws EntityAlreadyExistsException;
	void guardarSeguidor(Seguidores nuevoSeguidor)throws EntityAlreadyExistsException;
	void delete(String email) throws EntityNotFoundException;
	void reinicioBaseDatos() throws Exception;
	Usuarios usuariosFindByEmail (String email) throws EntityNotFoundException;
	
}
