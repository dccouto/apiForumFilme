package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.interfaces.GrupoBusinessInterface;
import com.challenge.dto.CriarGrupoDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/grupo")
@Api(value = "API Avaliação")
@CrossOrigin(origins = "*")
public class GrupoController {

	@Autowired
	private GrupoBusinessInterface grupoBusiness;

	@PostMapping("/criar")
	@ApiOperation(value = "Cria um grupo de discussão")
	public ResponseEntity<Object> criarGrupoDebate(@RequestBody CriarGrupoDto grupo) {

		return ResponseEntity.ok(grupoBusiness.criarGrupo(grupo));

	}

	@GetMapping("/buscar-grupos-publicos")
	@ApiOperation(value = "Busca todos grupos de discussão públicos")
	public ResponseEntity<Object> buscarGruposDebatePublicos() {

		return ResponseEntity.ok(grupoBusiness.buscarGruposDebatePublicos());
	}
}
