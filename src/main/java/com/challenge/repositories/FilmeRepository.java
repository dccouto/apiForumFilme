package com.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entities.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

	boolean existsByImdbID(String imdbID);

	List<Filme> findByListaFilmes_IdLista(Long idLista);

	Filme findByImdbID(String imdbID);

}
