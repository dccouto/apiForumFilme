package com.challenge.business.interfaces;

import com.challenge.dto.UsuarioDto;
import com.challenge.entities.Usuario;

public interface UsuarioBusinessInterface {

	void cadastrarUsuario(UsuarioDto usuarioDto);
	
	Usuario getUsuarioByEmail(String email);
	
	Usuario getUsuarioByIdUsuario(Long usuarioId);
}
