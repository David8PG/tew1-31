package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.persistence.FotoDao;


public class FotoBorrado {
	
	public void delete(String email) throws  EntityNotFoundException{
		
		FotoDao publi = Factories.persistence.createFotoDao();
		
		try {
			publi.delete(email);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
