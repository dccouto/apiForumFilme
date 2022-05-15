package com.challenge.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.GrupoBusinessInterface;
import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.CriarGrupoDto;
import com.challenge.entities.Grupo;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.exceptions.GrupoException;
import com.challenge.repositories.GrupoRepository;

@Service
class GrupoBusinessImpl implements GrupoBusinessInterface {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioBusinessInterface usuarioBusiness;

	@Override
	public Grupo criarGrupo(CriarGrupoDto grupo) {

		validarCamposObrigatoriosParaCriacaoGrupo(grupo);

		if (grupo.getStatus() == StatusAcesso.PRIVADO) {
			return criarGrupoPrivado(grupo);
		}
		return criarGrupoPublico(grupo);

	}

	private static void validarCamposObrigatoriosParaCriacaoGrupo(CriarGrupoDto grupo) {

		if (grupo.getStatus() == null) {
			throw new GrupoException("Necessário informar o status da grupo");
		}

		if (grupo.getStatus() == StatusAcesso.PRIVADO && grupo.getUsuariosAutorizados() == null) {
			throw new GrupoException("Necessário informar a lista de IDs dos usuários participantes");
		}

		if (grupo.getTitulo() == null) {
			throw new GrupoException("Necessário informar o título da grupo");
		}

		if (grupo.getUsuarioId() == null) {
			throw new GrupoException("Necessário informar o ID do responsável do grupo");
		}
	}

	private Grupo criarGrupoPrivado(CriarGrupoDto grupoDto) {

		Grupo grupoPrivado = grupoDtoParaGrupo(grupoDto);

		List<Usuario> usuarios = new ArrayList<>();

		for (Long id : grupoDto.getUsuariosAutorizados()) {
			Usuario usuario = usuarioBusiness.getUsuarioByIdUsuario(id);
			usuarios.add(usuario);
		}
		grupoPrivado.setUsuariosAutorizados(usuarios);

		return grupoRepository.save(grupoPrivado);

	}

	private Grupo criarGrupoPublico(CriarGrupoDto grupo) {
		Grupo grupoPublico = grupoDtoParaGrupo(grupo);
		return grupoRepository.save(grupoPublico);
	}

	private Grupo grupoDtoParaGrupo(CriarGrupoDto grupoDto) {

		Grupo grupo = new Grupo();
		BeanUtils.copyProperties(grupoDto, grupo);

		grupo.setAutor(usuarioBusiness.getUsuarioByIdUsuario(grupoDto.getUsuarioId()));

		return grupo;
	}

	@Override
	public List<Grupo> buscarGruposDebatePublicos() {
		return grupoRepository.findAllByStatus(StatusAcesso.PUBLICO);
	}

}
