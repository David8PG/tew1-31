package impl.tew.persistence;

import com.tew.persistence.SeguidoresDao;
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.FotoDao;
import com.tew.persistence.UsuariosDao;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UsuariosDao createUsuariosDao() {
		
		return new UsuariosJdbcDao();
	}

	@Override
	public FotoDao createFotoDao() {
	
		return new FotoJdbcDao();
	}

	@Override
	public SeguidoresDao createSeguidoresDao() {
		
		return new SeguidoresJdbcDao();
	}

}
