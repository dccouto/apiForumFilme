package com.challenge.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.UsuarioDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
@Api(value = "API Usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioBusinessInterface usuarioBusiness;

	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo usuário no sistema")
	public ResponseEntity<Object> criarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {

		usuarioBusiness.cadastrarUsuario(usuarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com Sucesso!");
	}
}
