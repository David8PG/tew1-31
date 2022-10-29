package impl.tew.business.classes;

import java.util.List;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.model.Seguidores;
import com.tew.persistence.SeguidoresDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;

public class SeguidoresOperaciones {
	
	public void save(Seguidores s) throws EntityAlreadyExistsException{
		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();

		try {
			seguidores.save(s);
		}
		catch(AlreadyPersistedException e){
			throw new EntityAlreadyExistsException("No se ha podido guardar la solicitud al seguidor " + s + e);
		}
	}
	
	public Seguidores findByEmail(String email_usuario) throws Exception{

		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		return seguidores.findByEmail(email_usuario);

	}

	public List<Seguidores> getSeguidores() throws Exception{

		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		return seguidores.getSeguidores();
	}

	public List<Seguidores> getSeguidores(String email_usuario) {
		
		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		return seguidores.getSeguidores(email_usuario);
	}
	
	
	public List<Seguidores> getCandiatos(String email_usuario) {

		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		return seguidores.getCandidatos(email_usuario);
	}

	
	
	public void delete(String email_usuario) throws EntityNotFoundException{

		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();

		try {
			seguidores.delete(email_usuario);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void aceptar(String email_usuario, String email_seguidor) throws EntityNotFoundException{
		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		try {
			seguidores.aceptar(email_usuario,email_seguidor);
			
		} catch (NotPersistedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete1(String email_usuario, String email_seguidor) {
		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();

		try {
			seguidores.delete1(email_usuario,email_seguidor);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Seguidores> getCandidatos1(String email_usuario) {

		SeguidoresDao seguidores = Factories.persistence.createSeguidoresDao();
		return seguidores.getCandidatos1(email_usuario);
	}
}