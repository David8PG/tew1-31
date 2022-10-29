package com.tew.model;

public class Seguidores {
	private String email_usuario;
	private String email_seguidor;
	private boolean aceptada;
	
	
	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	public String getEmail_seguidor() {
		return email_seguidor;
	}
	public void setEmail_seguidor(String email_seguidor) {
		this.email_seguidor = email_seguidor;
	}
	public boolean isAceptada() {
		return aceptada;
	}
	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}
	
}
