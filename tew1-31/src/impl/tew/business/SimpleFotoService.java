package impl.tew.business;

import java.util.List;

import com.tew.business.FotoService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Foto;

import impl.tew.business.classes.FotoAlta;
import impl.tew.business.classes.FotoBorrado;
import impl.tew.business.classes.FotoBuscar;
import impl.tew.business.classes.FotoListado;

public class SimpleFotoService implements FotoService{

	@Override
	public List<Foto> getFotos() throws Exception {
		
		return new FotoListado().getFotos();
		
	}
	
	@Override
	public List<Foto> getFotos(String email) throws Exception {
		
		return new FotoListado().getFotos(email);
		
	}
	
	@Override
	public List<Foto> getFotosSeguidores(String email) {
		
		return new FotoListado().getFotosSeguidores(email);
	}

	@Override
	public void save(Foto p) throws EntityAlreadyExistsException {
		
		new FotoAlta().save(p);
		
	}

	@Override
	public void update(Foto p) throws EntityNotFoundException {
		
	}

	@Override
	public void delete(String email) throws Exception {
		new FotoBorrado().delete(email);
		
		
	}

	@Override
	public Foto findById(Long id) throws Exception {

		return new FotoBuscar().findById(id);
	}

	@Override
	public void delete1(Foto f) throws Exception {
	
		new FotoBorrado().delete1(f);
	}
}
