package com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

	boolean existsByEmail(String email);
}
