package com.tew.presentation;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.tew.business.FotoService;
import com.tew.infraestructure.Factories;
import com.tew.model.Foto;

@ManagedBean
@SessionScoped
public class BeanFotos implements Serializable {

	private static final long serialVersionUID = -1786803583194749666L;
	


	private Foto [] fotos = null;

	@ManagedProperty(value = "#{login}")
	private BeanLogin login;

	@ManagedProperty(value = "#{foto}")
	private BeanFoto foto;


	public BeanFoto getFoto() {
		return foto;
	}
	public void setFoto(BeanFoto Foto) {

		this.foto = Foto;
	}	
	public Foto[] getFotos() {
		return fotos;
	}
	public void setFotos(Foto[] fotos) {
		this.fotos= fotos;
	}

	ArrayList<Foto> auxiliar = new ArrayList<Foto>();



	public ArrayList<Foto> getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(ArrayList<Foto> auxiliar) {
		this.auxiliar = auxiliar;
	}



	public void iniciaFoto(ActionEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		foto.setID(null);
		foto.setEmail(bundle.getString("valorEmail"));
		foto.setTitulo(bundle.getString("valorTitulo"));
		foto.setPath(bundle.getString("valorPath"));
		foto.setFecha(0L);
	}


	public String listado(){

		FotoService service;
		try {

			service= Factories.services.createFotoService();
			fotos = (Foto []) service.getFotos().toArray(new Foto[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String listadoEmail(){

		FotoService service;
		try {

			service= Factories.services.createFotoService();
			fotos = (Foto []) service.getFotos(login.getEmail()).toArray(new Foto[0]);

			auxiliar = new ArrayList<Foto>();
			for(int i=0; i<fotos.length;i++) {

				auxiliar.add(fotos[i]);
			}

			return "exito2";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}


	public String listadoSeguidores(){

		FotoService service;
		try {

			service= Factories.services.createFotoService();
			fotos = (Foto []) service.getFotosSeguidores(login.getEmail()).toArray(new Foto[0]);

			auxiliar = new ArrayList<Foto>();
			for(int i=0; i<fotos.length;i++) {

				auxiliar.add(fotos[i]);
			}

			return "exitoAmis";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String verFotosSegui(String f){

		FotoService service;
		try {

			service= Factories.services.createFotoService();
			fotos = (Foto []) service.getFotos(f).toArray(new Foto[0]);

			auxiliar = new ArrayList<Foto>();
			for(int i=0; i<fotos.length;i++) {

				auxiliar.add(fotos[i]);
			}

			return "exito50";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}



	public String salva() {

		FotoService service;
		Foto f = new Foto();

		f.setEmail(login.getEmail());
		f.setTitulo(foto.getTitulo());
		f.setPath(foto.getPath());
		Date actual = new Date();
		long fecha= actual.getTime();
		f.setFecha(fecha);

		try {

			System.out.println(f.getTitulo());

			service = Factories.services.createFotoService();
			service.save(f);
			listadoEmail();

			return "exito4";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	
	public String subirFoto(FileUploadEvent upload) throws Exception {
		System.out.println("Subiendo foto");
		System.out.println(upload.getFile().getFileName());
		upload.getFile().write("S:/Local1-tew31/tew1-31/tew1-31/tew1-31/WebContent/data/"+upload.getFile().getFileName());
		FotoService service;
		Foto f = new Foto();
		f.setEmail(login.getEmail());
		f.setTitulo(foto.getTitulo());
		f.setPath("data/"+upload.getFile().getFileName());
		System.out.println("data/"+upload.getFile().getFileName());
		Date actual = new Date();
		long fecha= actual.getTime();
		f.setFecha(fecha);

		try {

			System.out.println(f.getTitulo());

			service = Factories.services.createFotoService();
			service.save(f);
			listadoEmail();

			return "exito8";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	
	
	public String editarFoto(FileUploadEvent upload) throws Exception {
		System.out.println("Subiendo foto");
		System.out.println(upload.getFile().getFileName());
		upload.getFile().write("S:/Local1-tew31/tew1-31/tew1-31/tew1-31/WebContent/data/"+upload.getFile().getFileName());
		FotoService service;
		Foto f = new Foto();
		f.setEmail(login.getEmail());
		f.setTitulo(foto.getTitulo());
		f.setPath("data/"+upload.getFile().getFileName());
		System.out.println("data/"+upload.getFile().getFileName());
		Date actual = new Date();
		long fecha= actual.getTime();
		f.setFecha(fecha);

		try {

			System.out.println(f.getTitulo());

			service = Factories.services.createFotoService();
			service.save(f);
			listadoEmail();

			return "exito407";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	

	public String eliminar( Foto f) {
		File imagen = new File("S:/work/tew1-31/tew1-31/WebContent/data/descarga.jpg");
		System.out.print("Path "+foto.getPath());
		FotoService service;
		f.setEmail(login.getEmail());
		f.setTitulo(foto.getTitulo());
		f.setPath(foto.getPath());
		Date actual = new Date();
		long fecha= actual.getTime();
		f.setFecha(fecha);

		try {
			
			     

			System.out.println(f.getTitulo());

			service = Factories.services.createFotoService();
			service.delete1(f);
			listadoEmail();
			imagen.delete();
			return "exito14";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	public String eliminar1(Foto f) {
		File imagen = new File("S:/work/tew1-31/tew1-31/WebContent/data/descarga.jpg");
		System.out.print("Path "+foto.getPath());
		FotoService service;
		f.setEmail(login.getEmail());
		f.setTitulo(foto.getTitulo());
		f.setPath(foto.getPath());
		Date actual = new Date();
		long fecha= actual.getTime();
		f.setFecha(fecha);
	

		try {

			System.out.println(f.getTitulo());

			service = Factories.services.createFotoService();
			service.delete1(f);
			listadoEmail();
			imagen.delete();

			return "exito104";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	
	@PostConstruct
	public void init() {

		System.out.println("BeanFotos - PostConstruct");
		foto = (BeanFoto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("foto"));
		if(foto==null) {
			System.out.println("No existe BeanFotos");
			foto = new BeanFoto();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto", foto);
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

		System.out.println("BeanFotos - PreDestroy");
	}	
}