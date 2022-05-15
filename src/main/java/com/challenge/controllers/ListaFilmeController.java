package com.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
@Api(value="API Lista Filmes")
@CrossOrigin(origins="*")
public class ListaFilmeController {
	
	
	@Autowired
	private ListaFilmeBusinessInterface listaFilmeBusiness;
	
	@Autowired
	private UsuarioLogadoService usuarioLogadoService;
	
	@GetMapping("/participante")
	@ApiOperation(value="Retorna todas as listas do usuário logado")
	public ResponseEntity<List<ListaFilme>> buscarTodasListasDoUsuarioLogado(){
		try {
			
			String username = usuarioLogadoService.getUsername();

			return ResponseEntity.ok(listaFilmeBusiness.buscarListasDoParticipante(username));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping
	@ApiOperation(value="Retorna todas as listas públicas")
	public ResponseEntity<Page<ListaFilme>> buscarTodasListasPublicas(@PageableDefault(size = 2, page = 0) Pageable pageable){
		try {
			return ResponseEntity.ok(listaFilmeBusiness.buscarTodasListaPublicas(pageable));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/filtrar/{tipoFiltro}/{filtro}/{idLista}")
	@ApiOperation(value="Retorna os filmes da lista filtrados por 'ano' ou 'diretor'")
	public ResponseEntity<Object> buscarFilmeListaPorFiltro(@PathVariable String filtro, @PathVariable String tipoFiltro, @PathVariable Long idLista){
		try {
			
			String username = usuarioLogadoService.getUsername();
			
			return ResponseEntity.ok(listaFilmeBusiness.buscarFilmeListaPorFiltro(filtro, tipoFiltro, idLista, username));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Cria nova lista de filme
	 * @param lista
	 * 
	 * */
	@PostMapping("/nova-lista")
	@ApiOperation(value="Cria uma nova lista de filmes")
	public ResponseEntity<Object> criarNovaLista(@RequestBody ListaFilme lista){
		try {
			String username = usuarioLogadoService.getUsername();
			return ResponseEntity.ok(listaFilmeBusiness.criarListaFilme(lista, username));		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("alterar-visibilidade/{idLista}")
	@ApiOperation(value="Altera a visibilidade da lista")
	public ResponseEntity<ListaFilme> alterarStatusLista(@RequestBody ListaFilme status, @PathVariable Long idLista) {
		try {
			String username = usuarioLogadoService.getUsername();
			return ResponseEntity.ok(listaFilmeBusiness.alterarStatusLista(status.getStatus(), idLista, username));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("/adicionar-filme/{idLista}")
	@ApiOperation(value="Adiciona um filme na lista do usuário")
	public ResponseEntity<Object> adicionarFilmeNaLista(@PathVariable Long idLista, @RequestBody Filme filme){
		try {
			String username = usuarioLogadoService.getUsername();
			return ResponseEntity.ok(listaFilmeBusiness.adicionarFilmeNaLista(idLista, filme, username));		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	

}
