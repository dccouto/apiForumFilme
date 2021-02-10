package com.challenge.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.challenge.business.ListaFilmeBusiness;
import com.challenge.entities.ListaFilme;
import com.challenge.enums.StatusAcesso;
import com.challenge.repository.ListaFilmeRepository;

@SpringBootTest
class ListaFilmeControllersTests {

	@Autowired
	ListaFilmeBusiness listaFilmeBusiness;
	
	@MockBean
	ListaFilmeRepository listaFilmeRepository;
	
	@Test
	void aoBuscarTodasListasPublicasDeveRetornarTodasAsListasPublicasCadastradas() throws Exception {
		ListaFilme listaFilme = new ListaFilme();
		
		when(listaFilmeRepository.findByStatus(StatusAcesso.PUBLICO)).thenReturn(Stream.
				of(listaFilme, listaFilme, listaFilme).collect(Collectors.toList()));
		
		assertEquals(3, listaFilmeBusiness.buscarTodasListaPublicas().size());
	}

}
