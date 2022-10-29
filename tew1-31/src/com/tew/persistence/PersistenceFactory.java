package com.tew.persistence;

public interface PersistenceFactory {
	
	UsuariosDao createUsuariosDao();
	FotoDao createFotoDao();
	SeguidoresDao createSeguidoresDao();
}
