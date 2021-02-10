package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entities.Grupo;
import com.challenge.enums.StatusAcesso;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	List<Grupo> findAllByStatus(StatusAcesso publico);

}
