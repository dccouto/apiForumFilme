package com.challenge.dto;

import java.util.ArrayList;
import java.util.List;

import com.challenge.enums.StatusAcesso;

public class CriarGrupoTO {

	private Long usuarioId; 
	private StatusAcesso status; 
	private String titulo;
	private List<Long> usuariosAutorizados = new ArrayList<>();
	
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public StatusAcesso getStatus() {
		return status;
	}
	public void setStatus(StatusAcesso status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Long> getUsuariosAutorizados() {
		return usuariosAutorizados;
	}
	public void setUsuariosAutorizados(List<Long> usuariosAutorizados) {
		this.usuariosAutorizados = usuariosAutorizados;
	}
	
	
	
}
