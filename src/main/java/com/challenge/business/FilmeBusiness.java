package com.challenge.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.TO.FilmeOmdbTO;
import com.challenge.entities.Filme;
import com.challenge.repository.FilmeRepository;

@Service
public class FilmeBusiness {
	
	@Value("${api.omdb.key}")
	private String API_KEY;
	
	@Value("${api.omdb.get.by.titulo}")
	private String API_TITULO;
	
	@Value("${api.omdb.get.by.imdbID}")
	private String API_IMDB;

	
	@Autowired
	FilmeRepository filmeRepository;
	
	public FilmeOmdbTO buscarFilmePorTitulo(String titulo) {
		
		return buscarFilmeApiExternaPorTitulo(titulo);
	}
	
	private FilmeOmdbTO buscarFilmeApiExternaPorTitulo(String titulo){
		RestTemplate restTemplate = new RestTemplate();
		FilmeOmdbTO filmeTO = restTemplate.getForObject(API_TITULO + titulo + API_KEY, FilmeOmdbTO.class);
		return filmeTO;
		
	}
	
	public FilmeOmdbTO buscarFilmeApiExternaPorImdb(String imdb){
		RestTemplate restTemplate = new RestTemplate();
		FilmeOmdbTO filmeTO = restTemplate.getForObject(API_IMDB + imdb + API_KEY, FilmeOmdbTO.class);
		return filmeTO;
		
	}
	
	public boolean verificarSeFilmeEstaSalvoNoBancoDados(String imdbID) {
		return filmeRepository.existsByImdbID(imdbID);
	}

	public Filme salvar(FilmeOmdbTO filmeApiExternaPorImdb) {
		return filmeRepository.save(new Filme(filmeApiExternaPorImdb));
		
	}

	public List<Filme> getFilmesPorLista(Long idLista) {
		return filmeRepository.findByListaFilmes_IdLista(idLista);
		
	}
	
	public Filme buscarFilmeBaseLocalPorImdbID(String imdbID) throws Exception {
		try {
			return filmeRepository.findByImdbID(imdbID);
		} catch (Exception e) {
			throw new Exception("Falha ao buscar filme na base de dados");
		}
	}

}
