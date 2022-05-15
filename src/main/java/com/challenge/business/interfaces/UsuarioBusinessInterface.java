package com.challenge.business.interfaces;

import com.challenge.entities.Usuario;

public interface UsuarioBusinessInterface {

	void cadastrarUsuario(Usuario usuario);
	
	Usuario getUsuarioByEmail(String email);
	
	Usuario getUsuarioByIdUsuario(Long usuarioId);
}
