package com.challenge.business.interfaces;

import java.util.List;

import com.challenge.dto.AvaliacaoDto;
import com.challenge.entities.Avaliacao;

public interface AvaliacaoBusinessInterface {

	Avaliacao avaliarFilme(AvaliacaoDto avaliacaoDto, String username);

	void excluirAvaliacao(Long idAvaliacao);

	List<Avaliacao> buscaAvaliacaoPublicasPorFilme(Long idFilme);

}
