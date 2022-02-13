package com.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeOmdbTO {
	
	@JsonProperty("Title")
	private String titulo;
	
	@JsonProperty("Year")
	private String ano;
	
	@JsonProperty("Released")
	private String dataLancamento;
	
	@JsonProperty("Genre")
	private String genero;
	
	@JsonProperty("Director")
	private String diretor;
	
	@JsonProperty("Poster")
	private String poster;
	
	private String imdbRating;
	
	private String imdbID;
	
	
	
	public FilmeOmdbTO() {};
	
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAno() {
		return ano;
	}
	
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getDiretor() {
		return diretor;
	}
	
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getImdbRating() {
		return imdbRating;
	}
	
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	public String getImdbID() {
		return imdbID;
	}
	
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	
	
}
