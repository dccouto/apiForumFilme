package com.challenge.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.challenge.business.interfaces.ListaFilmeBusinessInterface;
import com.challenge.entities.ListaFilme;
import com.challenge.enums.StatusAcesso;
import com.challenge.repositories.ListaFilmeRepository;

@SpringBootTest
class ListaFilmeControllersTests {

	@Autowired
	ListaFilmeBusinessInterface listaFilmeBusiness;
	
	@MockBean
	ListaFilmeRepository listaFilmeRepository;
	
	@Test
	void aoBuscarTodasListasPublicasDeveRetornarTodasAsListasPublicasCadastradas() throws Exception {
		ListaFilme filmes = new ListaFilme();		
		List<ListaFilme> lista = new ArrayList<>();
		lista.add(filmes);
		lista.add(filmes);
		lista.add(filmes);
		
		Page<ListaFilme> pagedResponse = new PageImpl<ListaFilme>(lista);
		
		when(listaFilmeRepository.findByStatus(StatusAcesso.PUBLICO, null)).thenReturn(pagedResponse);
		
		assertEquals(3, listaFilmeBusiness.buscarTodasListaPublicas(null).getSize());
	}

}
