package com.challenge.business.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.challenge.dto.FilmeOmdbDto;
import com.challenge.entities.Filme;
import com.challenge.entities.ListaFilme;
import com.challenge.enums.StatusAcesso;

public interface ListaFilmeBusinessInterface {

	List<ListaFilme> buscarListasDoParticipante(String email);

	ListaFilme criarListaFilme(ListaFilme lista, String username);

	ListaFilme adicionarFilmeNaLista(Long idLista, Filme filme, String username);

	ListaFilme alterarStatusLista(StatusAcesso statusAcesso, Long idLista, String username);

	List<FilmeOmdbDto> buscarFilmeListaPorFiltro(String filtro, String tipoFiltro, Long idLista, String username);

	Page<ListaFilme> buscarTodasListaPublicas(Pageable pageable);

}
