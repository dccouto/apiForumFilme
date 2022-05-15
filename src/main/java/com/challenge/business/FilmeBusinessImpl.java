package com.challenge.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.business.interfaces.FilmeBusinessInterface;
import com.challenge.dto.FilmeOmdbDto;
import com.challenge.entities.Filme;
import com.challenge.repositories.FilmeRepository;

@Service
class FilmeBusinessImpl implements FilmeBusinessInterface {

	@Value("${api.omdb.key}")
	private static String API_KEY;

	@Value("${api.omdb.get.by.titulo}")
	private static String API_TITULO;

	@Value("${api.omdb.get.by.imdbID}")
	private static String API_IMDB;

	@Autowired
	private FilmeRepository filmeRepository;

	@Override
	public FilmeOmdbDto buscarFilmePorTitulo(String titulo) {

		return buscarFilmeApiExternaPorTitulo(titulo);
	}

	private static FilmeOmdbDto buscarFilmeApiExternaPorTitulo(String titulo) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(API_TITULO + titulo + API_KEY, FilmeOmdbDto.class);
	}

	@Override
	public FilmeOmdbDto buscarFilmeApiExternaPorImdb(String imdb) {
		RestTemplate restTemplate = new RestTemplate();
		FilmeOmdbDto filmeTO = restTemplate.getForObject(API_IMDB + imdb + API_KEY, FilmeOmdbDto.class);
		return filmeTO;

	}

	@Override
	public boolean verificarSeFilmeEstaSalvoNoBancoDados(String imdbID) {
		return filmeRepository.existsByImdbID(imdbID);
	}

	@Override
	public Filme salvar(FilmeOmdbDto filmeApiExternaPorImdb) {
		return filmeRepository.save(new Filme(filmeApiExternaPorImdb));

	}

	@Override
	public List<Filme> getFilmesPorLista(Long idLista) {
		return filmeRepository.findByListaFilmes_IdLista(idLista);

	}

	@Override
	public Filme buscarFilmeBaseLocalPorImdbID(String imdbID) {
		return filmeRepository.findByImdbID(imdbID);
	}

}
