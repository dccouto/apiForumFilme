package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entities.ListaFilme;
import com.challenge.enums.StatusAcesso;

public interface ListaFilmeRepository extends JpaRepository<ListaFilme, Long>{

	boolean existsByNomeLista(String nomeLista);

	boolean existsByFilmes_ImdbIDAndIdLista(String imdbID, Long idLista);
	
	boolean existsByUsuario_emailAndIdLista(String username, Long idLista);
	
	boolean existsByUsuario_emailAndNomeLista(String username, String nomeLista);
	
	boolean existsByFilmes_ImdbID(String imdbID);
	
	List<ListaFilme> findByUsuario_IdUsuario(Long id);
	
	List<ListaFilme> findByUsuario_Email(String email);

	List<ListaFilme> findByStatus(StatusAcesso status);

	Optional<ListaFilme> findByIdLista(Long idLista);

	ListaFilme findByUsuario_emailAndIdLista(String username, Long idLista);



}
