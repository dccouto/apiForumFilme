package com.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entities.Avaliacao;
import com.challenge.enums.StatusAcesso;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

	boolean existsByFilme_IdFilmeAndUsuario_idUsuario(Long idFilme, Long idUsuario);

	Avaliacao findByFilme_IdFilmeAndUsuario_idUsuario(Long idFilme, Long idUsuario);

	Avaliacao findByIdAvaliacao(Long idAvaliacao);

	List<Avaliacao> findAllByFilme_idFilmeAndStatus(Long idFilme, StatusAcesso publico);

}
