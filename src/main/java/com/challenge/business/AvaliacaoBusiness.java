package com.challenge.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.TO.FilmeOmdbTO;
import com.challenge.entities.Avaliacao;
import com.challenge.entities.Filme;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.repository.AvaliacaoRepository;

@Service
public class AvaliacaoBusiness {

	@Autowired
	AvaliacaoRepository avaliacaoRepository;

	@Autowired
	UsuarioBusiness usuarioBusiness;

	@Autowired
	FilmeBusiness filmeBusiness;

	public Avaliacao avaliarFilme(Avaliacao avaliacao, String username) throws Exception {
		try {
			validarCamposObrigatorios(avaliacao, username);

			String imdbID = avaliacao.getFilme().getImdbID();

			Usuario usuario = usuarioBusiness.getUsuarioByEmail(username);
			avaliacao.setUsuario(usuario);

			Filme filme = new Filme();
			if (!filmeBusiness.verificarSeFilmeEstaSalvoNoBancoDados(imdbID)) {

				FilmeOmdbTO FilmeApiExterna = filmeBusiness.buscarFilmeApiExternaPorImdb(imdbID);
				filme = filmeBusiness.salvar(FilmeApiExterna);
			} else {
				filme = filmeBusiness.buscarFilmeBaseLocalPorImdbID(imdbID);

				if (verificarSeExisteAvaliacaoDoFilmePeloUsuario(filme, usuario)) {
					Avaliacao avaliacaoAntiga = buscarAvaliacaoDoFilme(filme, usuario);
					avaliacao.setIdAvaliacao(avaliacaoAntiga.getIdAvaliacao());
				}
			}

			avaliacao.setFilme(filme);

			return avaliacaoRepository.save(avaliacao);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private Avaliacao buscarAvaliacaoDoFilme(Filme filme, Usuario usuario) throws Exception {
		try {
			return avaliacaoRepository.findByFilme_IdFilmeAndUsuario_idUsuario(filme.getIdFilme(),
					usuario.getIdUsuario());

		} catch (Exception e) {
			throw new Exception("Falha ao buscar avaliação");
		}
	}

	private boolean verificarSeExisteAvaliacaoDoFilmePeloUsuario(Filme filme, Usuario usuario) throws Exception {
		try {
			return avaliacaoRepository.existsByFilme_IdFilmeAndUsuario_idUsuario(filme.getIdFilme(),
					usuario.getIdUsuario());

		} catch (Exception e) {
			throw new Exception("Falha ao verificar se existe avaliação");
		}
	}

	private void validarCamposObrigatorios(Avaliacao avaliacao, String username) throws Exception {
		if (avaliacao.getStatus() == null) {
			throw new Exception("É necessário informar se é PUBLICO ou PRIVADO");
		}
		if (avaliacao.getEstrela() == null) {
			throw new Exception("As estretas são obrigatórias");
		}
		if (avaliacao.getEstrela() < 1 || avaliacao.getEstrela() > 5) {
			throw new Exception("As estretas permitidas são de 1 à 5");
		}
		if (avaliacao.getNota() == null) {
			throw new Exception("A nota é obrigatória");
		}
		if (avaliacao.getNota() < 5 || avaliacao.getNota() > 10) {
			throw new Exception("As notas deverão estar no intervalo entre 5 à 10");
		}
		if (username == null) {
			throw new Exception("É necessário estar logado");
		}
		System.out.println(avaliacao.getFilme().getImdbID());
		if (avaliacao.getFilme().getImdbID() == null) {
			throw new Exception("É informar o imdbId do filme");
		}
	}

	public void excluirAvaliacao(Long idAvaliacao) throws Exception {
		try {
			
			System.out.println(idAvaliacao);
			Avaliacao orElseThrow =(Avaliacao) Optional.ofNullable(avaliacaoRepository.findByIdAvaliacao(idAvaliacao))
					.orElseThrow(()->new Exception("Usuário não encontrado."));
 
			avaliacaoRepository.delete(orElseThrow);

		} catch (Exception e) {
			throw new Exception("Erro ao excluir a avaliação");
		}
	}

	public List<Avaliacao> buscaAvaliacaoPublicasPorFilme(Long idFilme) throws Exception {
		try {
			return Optional.ofNullable(avaliacaoRepository.findAllByFilme_idFilmeAndStatus(idFilme, StatusAcesso.PUBLICO))
					.orElseThrow(()->new Exception("Usuário não encontrado."));
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
