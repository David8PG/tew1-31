package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infraestructure.Factories;
import com.tew.model.Foto;
import com.tew.persistence.FotoDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class FotoAlta {
	
	public void save(Foto f) throws EntityAlreadyExistsException{
		
		FotoDao foto = Factories.persistence.createFotoDao();
		
		try {
			foto.save(f);
		}
		catch(AlreadyPersistedException e) {
			throw new EntityAlreadyExistsException("Ya existe la foto " + f);
			
		}
		
		
	}

}
