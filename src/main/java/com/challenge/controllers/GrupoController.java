package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.TO.CriarGrupoTO;
import com.challenge.business.GrupoBusiness;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/grupo")
@Api(value="API Avaliação")
@CrossOrigin(origins="*")
public class GrupoController {
	
	@Autowired
	GrupoBusiness grupoBusiness;

	/**
	 * @param grupo
	 * */
	@PostMapping("/criar")
	@ApiOperation(value="Cria um grupo de discussão")
	public ResponseEntity<Object> criarGrupoDebate(@RequestBody CriarGrupoTO grupo) {
		try {
			return ResponseEntity.ok(grupoBusiness.criarGrupo(grupo));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping("/buscar-grupos-publicos")
	@ApiOperation(value="Busca todos grupos de discussão públicos")
	public ResponseEntity<Object> buscarGruposDebatePublicos() {
		try {
			return ResponseEntity.ok(grupoBusiness.buscarGruposDebatePublicos());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
