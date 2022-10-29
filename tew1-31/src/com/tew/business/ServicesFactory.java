package com.tew.business;

public interface ServicesFactory {
	UsuariosService createUsuariosService();
	FotoService createFotoService();
	SeguidoresService createSeguidoresService();
	LoginService createLoginService();
}
