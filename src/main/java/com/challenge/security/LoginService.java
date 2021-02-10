package com.challenge.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.challenge.entities.Usuario;
import com.challenge.repository.UsuarioRepository;

@Repository
@Transactional
public class LoginService implements UserDetailsService{

	@Autowired
	private  UsuarioRepository usuarioRepository;
	
	
	//Método que retorna um UserDatail
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		
		//Faz a busca do usuário por nome e verifica se a busca é nula atraves do "Optional.ofNullable", se for entra na exceção.
		Usuario login = Optional.ofNullable(usuarioRepository.findByEmail(usuario))
				.orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado."));
		return login;
			
	}
}
