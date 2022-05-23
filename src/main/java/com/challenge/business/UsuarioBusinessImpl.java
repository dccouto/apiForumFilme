package com.challenge.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.UsuarioDto;
import com.challenge.entities.Usuario;
import com.challenge.exceptions.UserException;
import com.challenge.repositories.UsuarioRepository;

@Service 
class UsuarioBusinessImpl implements UsuarioBusinessInterface {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void cadastrarUsuario(@Valid UsuarioDto usuarioDto) {


		validaSeUsuarioExiste(usuarioDto);

		usuarioDto.setSenha(encriptaSenha(usuarioDto.getSenha()));
		
		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);

	}

	private void validaSeUsuarioExiste(UsuarioDto usuarioDto) {
		if (usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
			throw new UserException("Usuario já existe");
		}
	}

	private static String encriptaSenha(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario getUsuarioByIdUsuario(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow();
	}

}
