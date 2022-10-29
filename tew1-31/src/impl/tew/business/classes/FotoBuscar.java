package impl.tew.business.classes;

import com.tew.infraestructure.Factories;
import com.tew.model.Foto;
import com.tew.persistence.FotoDao;


public class FotoBuscar {

	public Foto findById(Long id) throws Exception{

		FotoDao publi = Factories.persistence.createFotoDao();
		return publi.findById(id);

	}

}
