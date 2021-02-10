package com.challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.challenge.enums.StatusAcesso;

@Entity
@Table(name = "COMENTARIOS")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_COMENTARIO")
	private Long idComentario;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusAcesso status;
	
	@Column(name = "TEXTO")
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="FK_FILME")
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name="FK_USUARIO")
	private Usuario autor;

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public StatusAcesso getStatus() {
		return status;
	}

	public void setStatus(StatusAcesso status) {
		this.status = status;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	
	
}
















