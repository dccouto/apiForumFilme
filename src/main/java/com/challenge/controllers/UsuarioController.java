package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.UsuarioBusiness;
import com.challenge.entities.Usuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/usuario")
@Api(value="API Usuario")
@CrossOrigin(origins="*")
public class UsuarioController {
	
	@Autowired
	UsuarioBusiness usuarioBusiness;

	@PostMapping("/cadastrar")
	@ApiOperation(value="Cadastra um novo usuário no sistema")
	public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
		try {
			usuarioBusiness.cadastrarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com Sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}
}
