package com.challenge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.entities.Usuario;
import com.challenge.exceptions.BusinessException;
import com.challenge.exceptions.UserException;
import com.challenge.repository.UsuarioRepository;

@Service 
class UsuarioBusinessImpl implements UsuarioBusinessInterface {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void cadastrarUsuario(Usuario usuario) {

		validaCamposObrigatoriosUsuario(usuario);

		validaSeUsuarioExiste(usuario);

		usuario.setSenha(encriptaSenha(usuario.getPassword()));
		usuarioRepository.save(usuario);

	}

	private static void validaCamposObrigatoriosUsuario(Usuario usuario) {
		if (usuario.getNome() == null) {
			throw new BusinessException("O campo nome é obrigatório");
		}
		if (usuario.getEmail() == null) {
			throw new BusinessException("O campo e-mail é obrigatório");
		}
		if (usuario.getPassword() == null) {
			throw new BusinessException("O campo senha é obrigatório");
		}

	}

	private void validaSeUsuarioExiste(Usuario usuario) {
		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
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
