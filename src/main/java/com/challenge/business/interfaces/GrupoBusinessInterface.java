package com.challenge.business.interfaces;

import java.util.List;

import com.challenge.dto.CriarGrupoDto;
import com.challenge.entities.Grupo;

public interface GrupoBusinessInterface {

	Grupo criarGrupo(CriarGrupoDto grupo);

	List<Grupo> buscarGruposDebatePublicos();

}
