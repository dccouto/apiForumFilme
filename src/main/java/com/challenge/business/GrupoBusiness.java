package com.challenge.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.dto.CriarGrupoTO;
import com.challenge.entities.Grupo;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.repository.GrupoRepository;

@Service
public class GrupoBusiness {

	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	UsuarioBusiness usuarioBusiness;
	
	public Grupo criarGrupo(CriarGrupoTO grupo) throws Exception {
		try {
			validarCamposObrigatoriosParaCriacaoGrupo(grupo);
			if(grupo.getStatus() == StatusAcesso.PRIVADO) {
				return criarGrupoPrivado(grupo); 
			}
			return criarGrupoPublico(grupo);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private Grupo criarGrupoPublico(CriarGrupoTO grupo) throws Exception {
		try {
			Grupo grupoPublico = GrupoToPraGrupo(grupo);
			return grupoRepository.save(grupoPublico);
		} catch (Exception e) {
			throw new Exception("Falha ao criar o grupo público");
		}
	}
	

	private Grupo criarGrupoPrivado(CriarGrupoTO grupo) throws Exception {
		try {
			Grupo grupoPrivado = GrupoToPraGrupo(grupo);

			List<Usuario> usuarios = new ArrayList<>();
			
			for (Long id : grupo.getUsuariosAutorizados()) {
				Usuario usuario = usuarioBusiness.getUsuarioByIdUsuario(id);
				usuarios.add(usuario);
			}
			grupoPrivado.setUsuariosAutorizados(usuarios);
			
			return grupoRepository.save(grupoPrivado);
			
		} catch (Exception e) {
			throw new Exception("Falha ao criar o grupo privado");
		}
		
	}
	
	private Grupo GrupoToPraGrupo(CriarGrupoTO grupoTO) throws Exception {
		try {
			
			Grupo grupo = new Grupo();
			
			grupo.setAutor(usuarioBusiness.getUsuarioByIdUsuario(grupoTO.getUsuarioId()));
			grupo.setTitulo(grupoTO.getTitulo());
			grupo.setStatus(grupoTO.getStatus());
			return grupo;
		} catch (Exception e) {
			throw new Exception("Erro ao obter usuário");
		}
	}

	private void validarCamposObrigatoriosParaCriacaoGrupo(CriarGrupoTO grupo) throws Exception {
		
		if(grupo.getStatus() == null) {
			throw new Exception("Necessário informar o status da grupo");
		}
		
		if(grupo.getStatus() == StatusAcesso.PRIVADO && grupo.getUsuariosAutorizados() == null) {
			throw new Exception("Necessário informar a lista de IDs dos usuários participantes");
		}
		
		if(grupo.getTitulo() == null) {
			throw new Exception("Necessário informar o título da grupo");
		}
		
		if(grupo.getUsuarioId() == null) {
			throw new Exception("Necessário informar o ID do responsável do grupo");
		}
	}

	public List<Grupo> buscarGruposDebatePublicos() throws Exception {
		try {
			return grupoRepository.findAllByStatus(StatusAcesso.PUBLICO);
		} catch (Exception e) {
			throw new Exception("Erro ao buscar os grupos");
		}
	}
	
}
