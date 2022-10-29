package com.tew.infraestructure;

import com.tew.business.ServicesFactory;
import com.tew.persistence.PersistenceFactory;

import impl.tew.business.SimpleServicesFactory;
import impl.tew.persistence.SimplePersistenceFactory;

public class Factories {

	public static ServicesFactory services = new SimpleServicesFactory();
	public static PersistenceFactory persistence = new SimplePersistenceFactory();

}
