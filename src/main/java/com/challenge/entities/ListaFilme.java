package com.challenge.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "LISTAS_FILMES")
public class ListaFilme {
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_LISTA_FILME")
	private Long idLista;
	
	@Column(name = "NOME_LISTA")
	private String nomeLista;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusAcesso status;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne
	@JoinColumn(name="FK_USUARIO")
	private Usuario usuario;
	
	@ApiModelProperty(hidden = true)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name= "FILMES_LISTA_FILME", 
		joinColumns= @JoinColumn(name= "FK_LISTA_FILME"), 
		inverseJoinColumns = @JoinColumn(name= "FK_FILME"))
	private List<Filme> filmes = new ArrayList<>();
	
	
	public ListaFilme() {}
	

	public ListaFilme(Long idLista, String nomeLista, StatusAcesso status, Usuario usuario, List<Filme> filmes) {
		this.idLista = idLista;
		this.nomeLista = nomeLista;
		this.status = status;
		this.usuario = usuario;
		this.filmes = filmes;
	}

	public Long getIdLista() {
		return idLista;
	}

	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}

	public StatusAcesso getStatus() {
		return status;
	}

	public void setStatus(StatusAcesso status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Filme> getListaFilmes() {
		return filmes;
	}

	public void setListaFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public String getNomeLista() {
		return nomeLista;
	}

	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
	}
	
	
	
	
}
