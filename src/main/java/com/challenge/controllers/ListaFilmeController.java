package com.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.interfaces.ListaFilmeBusinessInterface;
import com.challenge.entities.Filme;
import com.challenge.entities.ListaFilme;
import com.challenge.security.UsuarioLogadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lista-filme")
@Api(value = "API Lista Filmes")
@CrossOrigin(origins = "*")
public class ListaFilmeController {

	@Autowired
	private ListaFilmeBusinessInterface listaFilmeBusiness;

	@Autowired
	private UsuarioLogadoService usuarioLogadoService;

	@GetMapping("/participante")
	@ApiOperation(value = "Retorna todas as listas do usuário logado")
	public ResponseEntity<List<ListaFilme>> buscarTodasListasDoUsuarioLogado() {

		String username = usuarioLogadoService.getUsername();

		return ResponseEntity.ok(listaFilmeBusiness.buscarListasDoParticipante(username));
	}

	@GetMapping
	@ApiOperation(value = "Retorna todas as listas públicas")
	public ResponseEntity<Page<ListaFilme>> buscarTodasListasPublicas(
			@PageableDefault(size = 2, page = 0) Pageable pageable) {

		return ResponseEntity.ok(listaFilmeBusiness.buscarTodasListaPublicas(pageable));
	}

	@GetMapping("/filtrar/{tipoFiltro}/{filtro}/{idLista}")
	@ApiOperation(value = "Retorna os filmes da lista filtrados por 'ano' ou 'diretor'")
	public ResponseEntity<Object> buscarFilmeListaPorFiltro(@PathVariable String filtro,
			@PathVariable String tipoFiltro, @PathVariable Long idLista) {

		String username = usuarioLogadoService.getUsername();

		return ResponseEntity.ok(listaFilmeBusiness.buscarFilmeListaPorFiltro(filtro, tipoFiltro, idLista, username));
	}

	@PostMapping("/nova-lista")
	@ApiOperation(value = "Cria uma nova lista de filmes")
	public ResponseEntity<Object> criarNovaLista(@RequestBody ListaFilme lista) {

		String username = usuarioLogadoService.getUsername();
		return ResponseEntity.ok(listaFilmeBusiness.criarListaFilme(lista, username));
	}

	@PutMapping("alterar-visibilidade/{idLista}")
	@ApiOperation(value = "Altera a visibilidade da lista")
	public ResponseEntity<ListaFilme> alterarStatusLista(@RequestBody ListaFilme status, @PathVariable Long idLista) {

		String username = usuarioLogadoService.getUsername();
		return ResponseEntity.ok(listaFilmeBusiness.alterarStatusLista(status.getStatus(), idLista, username));
	}

	@PostMapping("/adicionar-filme/{idLista}")
	@ApiOperation(value = "Adiciona um filme na lista do usuário")
	public ResponseEntity<Object> adicionarFilmeNaLista(@PathVariable Long idLista, @RequestBody Filme filme) {

		String username = usuarioLogadoService.getUsername();
		return ResponseEntity.ok(listaFilmeBusiness.adicionarFilmeNaLista(idLista, filme, username));
	}
}
