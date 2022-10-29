package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tew.model.Usuarios;

@ManagedBean(name = "usuario")
@SessionScoped
public class BeanUsuario extends Usuarios implements Serializable{

	private static final long serialVersionUID = 619277L;
	
	public BeanUsuario() {
		iniciaUsuario(null);
	}
	
	public void setUsuario(Usuarios u) {
		
		setEmail(u.getEmail());
		setNombre(u.getNombre());
		setPasswd(u.getPasswd());
		setRol(u.getRol());	
	}
	
	public void iniciaUsuario(ActionEvent event) {
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		setEmail(bundle.getString("valorDefectoCorreo"));
		setPasswd(bundle.getString("valorDefectoContra"));
		setRol("usuario");
		setNombre(bundle.getString("valorDefectoNombre"));
	}
}
