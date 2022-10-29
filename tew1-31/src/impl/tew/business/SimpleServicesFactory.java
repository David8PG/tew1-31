package impl.tew.business;

import com.tew.business.SeguidoresService;
import com.tew.business.LoginService;
import com.tew.business.FotoService;
import com.tew.business.ServicesFactory;
import com.tew.business.UsuariosService;

public class SimpleServicesFactory implements ServicesFactory{
	@Override
	public UsuariosService createUsuariosService() {
		
		return new SimpleUsuariosService();
	}

	@Override
	public FotoService createFotoService() {
	
		return new SimpleFotoService();
	}

	@Override
	public SeguidoresService createSeguidoresService() {
		
		return new SimpleSeguidoresService();
	}

	@Override
	public LoginService createLoginService() {
		
		return new SimpleLoginService();
	}

}
