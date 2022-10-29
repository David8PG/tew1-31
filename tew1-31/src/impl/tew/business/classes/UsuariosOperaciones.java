package impl.tew.business.classes;

import java.util.List;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.model.Seguidores;
import com.tew.model.Usuarios;
import com.tew.persistence.UsuariosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class UsuariosOperaciones {

	public void save(Usuarios u) throws EntityAlreadyExistsException{
		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();

		try {
			usuarios.save(u);
		}
		catch(AlreadyPersistedException e){
			throw new EntityAlreadyExistsException("El usuario " + u + "ya esta registrado" + e);
		}
	}
	
	public void guardarSeguidor(Seguidores nuevoSeguidor) throws EntityAlreadyExistsException {
		
		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();

		try {
			usuarios.guardarSeguidor(nuevoSeguidor);
		}
		catch(AlreadyPersistedException e){
			throw new EntityAlreadyExistsException("El usuario " + nuevoSeguidor + "ya esta registrado" + e);
		}
		
	}

	public void delete(String email) throws EntityNotFoundException{
		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();

		try {
			usuarios.delete(email);
		}
		catch(NotPersistedException e){
			throw new EntityNotFoundException("El usuario con email" + email + "no se encuantra en la base de datos" + e);
		}
	}

	public Usuarios findByEmail(String email) throws EntityNotFoundException{

		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();
		return usuarios.findByEmail(email);

	}

	public void reiniciaBD() throws Exception{

		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();

		try {
			System.out.println("Llego a usuariosOperaciones");
			usuarios.reset();
		}
		catch(Exception e){
			throw new Exception("No se ha podido reiniciar la base de datos" + e);
		}
	}

	public List<Usuarios> getUsuarios() throws Exception{

		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();
		return usuarios.getUsuarios();
	}

	public List<Usuarios> getUsuarios(String filtro, String email) {
		
		UsuariosDao usuarios = Factories.persistence.createUsuariosDao();
		return usuarios.getUsuarios(filtro, email);
	}

}
