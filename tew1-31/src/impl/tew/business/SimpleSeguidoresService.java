package impl.tew.business;

import java.util.List;

import com.tew.business.SeguidoresService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Seguidores;

import impl.tew.business.classes.SeguidoresOperaciones;

public class SimpleSeguidoresService implements SeguidoresService{

	@Override
	public List<Seguidores> getSeguidores() throws Exception {
		
		return new SeguidoresOperaciones().getSeguidores();
	}
	
	@Override
	public List<Seguidores> getSeguidores(String email_usuario) throws Exception {
		
		return new SeguidoresOperaciones().getSeguidores(email_usuario);
	}
	
	
	@Override
	public List<Seguidores> getCandidatos(String email_usuario) throws Exception {
	
		return new SeguidoresOperaciones().getCandiatos(email_usuario);
	}
	
	@Override
	public void save(Seguidores a) throws EntityAlreadyExistsException {
		
		new SeguidoresOperaciones().save(a);
	}

	@Override
	public void update(Seguidores a) throws EntityNotFoundException {
		
		
	}

	@Override
	public void delete(String email_usuario) throws EntityNotFoundException {
		new SeguidoresOperaciones().delete(email_usuario);
		
		
	}

	

	@Override
	public Seguidores findByEmail(String email_usuario) throws Exception {
		
		return new SeguidoresOperaciones().findByEmail(email_usuario);
	}

	@Override
	public void aceptar(String email_usuario, String email_seguidor) throws EntityNotFoundException {
		new SeguidoresOperaciones().aceptar(email_usuario,email_seguidor);
		
	}

	@Override
	public void delete1(String email_usuario, String email_seguidor) throws EntityNotFoundException {
		new SeguidoresOperaciones().delete1(email_usuario,email_seguidor);
		
	}

	@Override
	public List<Seguidores> getCandidatos1(String email) throws Exception {

		return new SeguidoresOperaciones().getCandidatos1(email);
	}


}
