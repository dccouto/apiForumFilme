package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.FilmeBusiness;
import com.challenge.dto.FilmeOmdbDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/filme")
@Api(value="API Filmes")
@CrossOrigin(origins="*")
public class FilmeController {
	
	@Autowired
	FilmeBusiness filmeBusiness;
	
	
	@Cacheable(value="buscarFilme")
	@GetMapping("/{titulo}")
	@ApiOperation(value="Busca um filme pelo título")
	public ResponseEntity<FilmeOmdbDto> buscarFilmePorTitulo(@PathVariable String titulo){
		try {			
			FilmeOmdbDto buscarFilmePorTitulo = filmeBusiness.buscarFilmePorTitulo(titulo);
			if(buscarFilmePorTitulo.getImdbID() == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			return ResponseEntity.ok(filmeBusiness.buscarFilmePorTitulo(titulo));		

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

}
