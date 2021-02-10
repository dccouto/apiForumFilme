package com.challenge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.entities.Usuario;
import com.challenge.repository.UsuarioRepository;

@Service
public class UsuarioBusiness {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public void cadastrarUsuario(Usuario usuario) throws Exception {
		
		
		validaUsuario(usuario);
		
		try {
			if(usuarioRepository.existsByEmail(usuario.getEmail())) {
				throw new Exception("Usuario já existe");
			}
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
			usuarioRepository.save(usuario);
			
		} catch (Exception e) {
			throw new Exception("Erro ao persistir");
		}
		
	}

	private void validaUsuario(Usuario usuario) throws Exception {
		if(usuario.getNome() == null) {
			throw new Exception("O campo nome é obrigatório");
		}
		if(usuario.getEmail() == null) {
			throw new Exception("O campo e-mail é obrigatório");	
		}
		if(usuario.getPassword() == null) {
			throw new Exception("O campo senha é obrigatório");
		}
		
	}

	public Usuario getUsuarioByEmail(String email) throws Exception {
		try {
			return usuarioRepository.findByEmail(email);
			
		} catch (Exception e) {
			throw new Exception("Usuário não encontrado");
		}
	}

	public Usuario getUsuarioByIdUsuario(Long usuarioId) throws Exception {
		try {
			return usuarioRepository.findById(usuarioId).get();
		} catch (Exception e) {
			throw new Exception("Usuário não encontrado");
		}
	}

	
}
