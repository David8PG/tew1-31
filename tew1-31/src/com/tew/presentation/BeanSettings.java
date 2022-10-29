package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty (value = "#{usuario}")
	private BeanUsuario usuario;

	@ManagedProperty (value = "#{foto}")
	private BeanFoto foto;

	@ManagedProperty (value = "#{seguidor}")
	private BeanSeguidor seguidor;
	
	

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	public BeanFoto getFoto() {
		return foto;
	}

	public void setFoto(BeanFoto foto) {
		this.foto = foto;
	}

	public BeanSeguidor getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(BeanSeguidor seguidor) {
		this.seguidor = seguidor;
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de cÃ³digo para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida â€œesâ€�
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				usuario.iniciaUsuario(null);
			
			if(foto != null) {
					foto.iniciaFoto(null);
			}

			if(seguidor != null) {
					seguidor.iniciaSeguidor(null);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				usuario.iniciaUsuario(null);
			
			if(foto != null) {
					foto.iniciaFoto(null);
			}

			if(seguidor != null) {
					seguidor.iniciaSeguidor(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesiÃ³n)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavÃ­a si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el alumno en la sesiÃ³n. Esto es un patrÃ³n factorÃ­a
		// claramente.
		usuario = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));
		// si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanUsuario - No existia");
			usuario = new BeanUsuario();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuario", usuario);
		}
		
		foto = (BeanFoto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("foto"));
		if(foto==null) {
			System.out.println("No existe BeanFotos");
			foto = new BeanFoto();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto", foto);
		}

		seguidor = (BeanSeguidor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("seguidor"));
		if(seguidor==null) {
			System.out.println("No existe BeanSeguidores");
			seguidor = new BeanSeguidor();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seguidor", seguidor);
		}
	}

	// Es sÃ³lo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}

