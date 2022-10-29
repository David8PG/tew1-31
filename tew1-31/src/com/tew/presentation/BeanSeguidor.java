package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tew.model.Seguidores;

@ManagedBean(name = "seguidor")
@SessionScoped
public class BeanSeguidor extends Seguidores implements Serializable {

	private static final long serialVersionUID = -254891L;

	public BeanSeguidor() {
		iniciaSeguidor(null);
	}
	
	public void setSeguidor(Seguidores s) {
		setEmail_usuario(s.getEmail_usuario());
		setEmail_seguidor(s.getEmail_seguidor());
		setAceptada(s.isAceptada());
	}
	
	public void iniciaSeguidor(ActionEvent event) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		setEmail_usuario(bundle.getString("valorDefectoNombre"));
		setEmail_seguidor(bundle.getString("valorDefectoNombre"));
		setAceptada(true);
	}
}

