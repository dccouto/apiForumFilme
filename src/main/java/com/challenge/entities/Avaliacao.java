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
import javax.validation.Valid;

import com.challenge.dto.AvaliacaoDto;
import com.challenge.enums.StatusAcesso;

import springfox.documentation.annotations.ApiIgnore;

@Entity
@Table(name = "AVALIACOES")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_AVALIACAO")
	private Long idAvaliacao;
	
	@Column(name = "NOTA")
	private Long nota;
	
	@Column(name = "ESTRELA")
	private Integer estrela;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusAcesso status;
	
	@ManyToOne
	@JoinColumn(name="FK_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="FK_FILME")
	private Filme filme;

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Long getNota() {
		return nota;
	}

	public void setNota(Long nota) {
		this.nota = nota;
	}

	public Integer getEstrela() {
		return estrela;
	}

	public void setEstrela(Integer estrela) {
		this.estrela = estrela;
	}
	@ApiIgnore
	public Usuario getUsuario() {
		return usuario;
	}

	@ApiIgnore
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public StatusAcesso getStatus() {
		return status;
	}

	public void setStatus(StatusAcesso status) {
		this.status = status;
	}

	public Avaliacao toEntity(@Valid AvaliacaoDto avaliacaoDto) {
		this.setEstrela(avaliacaoDto.getEstrela());
		this.setNota(avaliacaoDto.getNota());
		this.setStatus(avaliacaoDto.getStatus());
		return this;
	}
	
	
	
	
	
}
