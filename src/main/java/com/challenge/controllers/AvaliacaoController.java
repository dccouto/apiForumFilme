package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.interfaces.AvaliacaoBusinessInterface;
import com.challenge.entities.Avaliacao;
import com.challenge.security.UsuarioLogadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/filme/avaliar")
@Api(value = "API Avaliação")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoBusinessInterface avaliacaoBusiness;

	@Autowired
	UsuarioLogadoService usuarioLogadoService;

	@GetMapping("/avaliacoes-por-filme/{idFilme}")
	@ApiOperation(value = "Busca todas avaliações publicas por filme")
	public ResponseEntity<Object> buscaAvaliacaoPublicasPorFilme(@PathVariable Long idFilme) {

		return ResponseEntity.ok(avaliacaoBusiness.buscaAvaliacaoPublicasPorFilme(idFilme));
	}

	@PostMapping
	@ApiOperation(value = "Avalia um filme através de nota e estrela")
	public ResponseEntity<Object> avaliarFilme(@RequestBody Avaliacao avaliacao) {

		String username = usuarioLogadoService.getUsername();
		return ResponseEntity.ok(avaliacaoBusiness.avaliarFilme(avaliacao, username));
	}

	@DeleteMapping("/excluir/{idAvaliacao}")
	@ApiOperation(value = "Exclui uma avaliação de nota e estrela")
	public ResponseEntity<String> excluirAvaliacao(@PathVariable Long idAvaliacao) {

		avaliacaoBusiness.excluirAvaliacao(idAvaliacao);
		return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso");
	}
}
