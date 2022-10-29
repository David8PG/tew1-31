package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.SeguidoresService;
import com.tew.infraestructure.Factories;
import com.tew.model.Seguidores;
import com.tew.model.Usuarios;


@ManagedBean
@SessionScoped
public class BeanSeguidores implements Serializable {

	private static final long serialVersionUID = 8292307775503190304L;
	
	private Seguidores [] seguidores = null;

	@ManagedProperty(value = "#{seguidor}")
	private BeanSeguidor seguidor;
	

	@ManagedProperty(value = "#{login}")
	private BeanLogin login;
	
	
	
	public BeanSeguidor getFoto() {
		return seguidor;
	}
	public void setSeguidor(BeanSeguidor seguidor) {

		this.seguidor = seguidor;
	}
	public Seguidores[] getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(Seguidores[] seguidores) {
		this.seguidores = seguidores;
	}

	
	
	public void iniciaSeguidores(ActionEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		seguidor.setEmail_usuario(bundle.getString("valorEmail_usuario"));
		seguidor.setEmail_seguidor(bundle.getString("valorEmail_seguidores"));
		seguidor.setAceptada(true);
	}


	
	
	
	public String listado(){

		SeguidoresService service;
		try {

			service= Factories.services.createSeguidoresService();
			seguidores = (Seguidores []) service.getSeguidores().toArray(new Seguidores[0]);
			return "exito3000";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public String listadoEmail2(){

		SeguidoresService service;
		try {

			service= Factories.services.createSeguidoresService();
			seguidores = (Seguidores []) service.getSeguidores(login.getEmail()).toArray(new Seguidores[0]);
			return "exito3";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String buscarSeguidores() {
		
		
		SeguidoresService service;
		try {

			service= Factories.services.createSeguidoresService();
			seguidores = (Seguidores []) service.getCandidatos(login.getEmail()).toArray(new Seguidores[0]);
			return "exitoBuscar2";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	public String eliminarSeguidor(String s) {

		SeguidoresService service;
		
		try {
			
			service= Factories.services.createSeguidoresService();
			service.delete1(login.getEmail(),s);
			seguidores = (Seguidores []) service.getCandidatos1(login.getEmail()).toArray(new Seguidores[0]);
			return "exito47";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
		
	
	
	public String aceptar(String email_usuario) {
		
		SeguidoresService service;
		try {
			
			System.out.println("EMAIL_Seguidor: " + email_usuario);
			System.out.println("EMAIL CON LOGIN" + login.getEmail());
			service= Factories.services.createSeguidoresService();
			
			service.aceptar(email_usuario,login.getEmail());
			
			seguidores = (Seguidores []) service.getSeguidores(login.getEmail()).toArray(new Seguidores[0]);
			
			return "LCAP";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}		
	}

	
	
	
	public String salva() {

		SeguidoresService service;
		try {
			
			service = Factories.services.createSeguidoresService();
			service.save(seguidor);
			seguidores = (Seguidores []) service.getSeguidores().toArray(new Seguidores[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	
	
	
	@PostConstruct
	public void init() {

		System.out.println("BeanSeguidores - PostConstruct");
		seguidor = (BeanSeguidor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("Seguidor"));
		if(seguidor==null) {
			System.out.println("No existe BeanSeguidores");
			seguidor = new BeanSeguidor();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Seguidor", seguidor);
		}
		login = (BeanLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("login"));
		if(login==null) {
			System.out.println("No existe BeanLogin");
			login = new BeanLogin();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", login);
		}
	}

	@PreDestroy
	public void end() {

		System.out.println("BeanSeguidores - PreDestroy");
	}
}