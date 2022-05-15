package com.challenge.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.AvaliacaoBusinessInterface;
import com.challenge.business.interfaces.FilmeBusinessInterface;
import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.FilmeOmdbDto;
import com.challenge.entities.Avaliacao;
import com.challenge.entities.Filme;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.exceptions.AvalicacaoException;
import com.challenge.repositories.AvaliacaoRepository;

@Service
class AvaliacaoBusinessImpl implements AvaliacaoBusinessInterface {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private UsuarioBusinessInterface usuarioBusiness;

	@Autowired
	private FilmeBusinessInterface filmeBusiness;

	@Override
	public Avaliacao avaliarFilme(Avaliacao avaliacao, String username) {
		validarCamposObrigatorios(avaliacao, username);

		String imdbID = avaliacao.getFilme().getImdbID();

		Usuario usuario = usuarioBusiness.getUsuarioByEmail(username);
		avaliacao.setUsuario(usuario);

		Filme filme = new Filme();
		if (!filmeBusiness.verificarSeFilmeEstaSalvoNoBancoDados(imdbID)) {

			FilmeOmdbDto FilmeApiExterna = filmeBusiness.buscarFilmeApiExternaPorImdb(imdbID);
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
	}

	private static void validarCamposObrigatorios(Avaliacao avaliacao, String username) {
		if (avaliacao.getStatus() == null) {
			throw new AvalicacaoException("É necessário informar se é PUBLICO ou PRIVADO");
		}
		if (avaliacao.getEstrela() == null) {
			throw new AvalicacaoException("As estretas são obrigatórias");
		}
		if (avaliacao.getEstrela() < 1 || avaliacao.getEstrela() > 5) {
			throw new AvalicacaoException("As estretas permitidas são de 1 à 5");
		}
		if (avaliacao.getNota() == null) {
			throw new AvalicacaoException("A nota é obrigatória");
		}
		if (avaliacao.getNota() < 5 || avaliacao.getNota() > 10) {
			throw new AvalicacaoException("As notas deverão estar no intervalo entre 5 à 10");
		}
		if (username == null) {
			throw new AvalicacaoException("É necessário estar logado");
		}
		if (avaliacao.getFilme().getImdbID() == null) {
			throw new AvalicacaoException("É informar o imdbId do filme");
		}
	}

	private Avaliacao buscarAvaliacaoDoFilme(Filme filme, Usuario usuario) {
		return avaliacaoRepository.findByFilme_IdFilmeAndUsuario_idUsuario(filme.getIdFilme(), usuario.getIdUsuario());
	}

	private boolean verificarSeExisteAvaliacaoDoFilmePeloUsuario(Filme filme, Usuario usuario) {
		return avaliacaoRepository.existsByFilme_IdFilmeAndUsuario_idUsuario(filme.getIdFilme(),
				usuario.getIdUsuario());

	}

	@Override
	public void excluirAvaliacao(Long idAvaliacao) {

		System.out.println(idAvaliacao);
		Avaliacao avaliacao = (Avaliacao) Optional.ofNullable(avaliacaoRepository.findByIdAvaliacao(idAvaliacao))
				.orElseThrow(() -> new AvalicacaoException("Usuário não encontrado."));

		avaliacaoRepository.delete(avaliacao);

	}

	@Override
	public List<Avaliacao> buscaAvaliacaoPublicasPorFilme(Long idFilme) {
		return Optional.ofNullable(avaliacaoRepository.findAllByFilme_idFilmeAndStatus(idFilme, StatusAcesso.PUBLICO))
				.orElseThrow(() -> new AvalicacaoException("Usuário não encontrado."));

	}

}
