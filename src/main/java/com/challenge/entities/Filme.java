package com.challenge.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.challenge.dto.FilmeOmdbDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "FILMES")
public class Filme {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_FILME")
	private Long idFilme;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "IMDB_ID")
	private String imdbID;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "filmes", fetch = FetchType.LAZY)	
	private List<ListaFilme> listaFilmes = new ArrayList<>();
	
	
	public Filme() {};
	
	public Filme(FilmeOmdbDto filmeOmdbTO) {
		this.setTitulo(filmeOmdbTO.getTitulo());
		this.setImdbID(filmeOmdbTO.getImdbID());
	}
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	 public List<ListaFilme> getListaFilmes() { return listaFilmes; }
	  
	 public void setListaFilmes(List<ListaFilme> listaFilmes) { this.listaFilmes =  listaFilmes; }
	 

	public Long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Long idFilme) {
		this.idFilme = idFilme;
	}


	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	
		
}
