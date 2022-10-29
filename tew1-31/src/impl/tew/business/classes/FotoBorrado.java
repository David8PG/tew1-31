package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infraestructure.Factories;
import com.tew.model.Foto;
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
public void delete1(Foto f) throws  EntityNotFoundException{
		
		FotoDao publi = Factories.persistence.createFotoDao();
		
		try {
			publi.delete1(f);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
