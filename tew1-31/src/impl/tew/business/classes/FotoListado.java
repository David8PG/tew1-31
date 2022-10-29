package impl.tew.business.classes;

import java.util.List;

import com.tew.infraestructure.Factories;
import com.tew.model.Foto;
import com.tew.persistence.FotoDao;

public class FotoListado {
	
		
		public List<Foto> getFotos() throws Exception {
			
			FotoDao publi = Factories.persistence.createFotoDao();
			return publi.getFotos();

		}

		public List<Foto> getFotos(String email) {
			
			FotoDao publi = Factories.persistence.createFotoDao();
			return publi.getFotos(email);
			
		}

		public List<Foto> getFotosSeguidores(String email) {
			
			FotoDao publi = Factories.persistence.createFotoDao();
			return publi.getFotosSeguidores(email);
		}
}
