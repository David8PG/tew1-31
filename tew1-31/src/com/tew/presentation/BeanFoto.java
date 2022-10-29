package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tew.model.Foto;

@ManagedBean(name = "foto")
@SessionScoped

public class BeanFoto extends Foto implements Serializable {
	private static final long serialVersionUID = 207L;
		

		public BeanFoto() {
			iniciaFoto(null);
		}
		
		public void setFoto(Foto f) {
			setID(f.getID());
			setPath(f.getPath());
			setTitulo(f.getTitulo());
			setEmail(f.getEmail());
			setFecha(f.getFecha());	
		}
		
		public void iniciaFoto(ActionEvent event) {
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
			setID(null);
			setEmail(bundle.getString("valorDefectoNombre"));
			setTitulo(bundle.getString("tablaTitulo"));
			setPath(bundle.getString("ruta"));
			setFecha(0L);
		}

	
		
	

}
