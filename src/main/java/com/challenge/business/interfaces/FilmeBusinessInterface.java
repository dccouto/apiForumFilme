package com.challenge.business.interfaces;

import java.util.List;

import com.challenge.dto.FilmeOmdbDto;
import com.challenge.entities.Filme;

public interface FilmeBusinessInterface {

	FilmeOmdbDto buscarFilmePorTitulo(String titulo);

	FilmeOmdbDto buscarFilmeApiExternaPorImdb(String imdb);

	boolean verificarSeFilmeEstaSalvoNoBancoDados(String imdbID);

	Filme salvar(FilmeOmdbDto filmeApiExternaPorImdb);

	List<Filme> getFilmesPorLista(Long idLista);

	Filme buscarFilmeBaseLocalPorImdbID(String imdbID);

}
