package com.challenge.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.challenge.enums.StatusAcesso;

@Entity
@Table(name = "GRUPOS_DEBATE")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_GRUPO_DEBATE")
	private Long idGrupoDebate;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusAcesso status;
	
	@ManyToMany
	@JoinTable(name= "USUARIOS_GRUPOS", 
		joinColumns= @JoinColumn(name= "FK_GRUPO_DEBATE"),
		inverseJoinColumns = @JoinColumn(name= "FK_USUARIO"))
	private List<Usuario> usuariosAutorizados;
	
	@ManyToOne
	@JoinColumn(name = "FK_USUARIO")
	private Usuario autor;
	
	public Grupo() {
		
	}

	public Long getIdUsuario() {
		return idGrupoDebate;
	}

	public void setIdUsuario(Long idGrupoDebate) {
		this.idGrupoDebate = idGrupoDebate;
	}


	public Long getIdGrupoDebate() {
		return idGrupoDebate;
	}

	public void setIdGrupoDebate(Long idGrupoDebate) {
		this.idGrupoDebate = idGrupoDebate;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StatusAcesso getStatus() {
		return status;
	}

	public void setStatus(StatusAcesso status) {
		this.status = status;
	}

	public List<Usuario> getUsuariosAutorizados() {
		return usuariosAutorizados;
	}

	public void setUsuariosAutorizados(List<Usuario> usuariosAutorizados) {
		this.usuariosAutorizados = usuariosAutorizados;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	
	
}
