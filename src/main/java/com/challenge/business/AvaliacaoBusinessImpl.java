package com.challenge.business;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.AvaliacaoBusinessInterface;
import com.challenge.business.interfaces.FilmeBusinessInterface;
import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.AvaliacaoDto;
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
	public Avaliacao avaliarFilme(@Valid AvaliacaoDto avaliacaoDto, @Valid String username) {

		var usuario = buscaUsuarioPorEmail(username);
		
		Avaliacao avaliacao = new Avaliacao().toEntity(avaliacaoDto);
		
		avaliacao.setUsuario(usuario);

		Filme filme = new Filme();
		
		String imdbID = avaliacaoDto.getImdbID();
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

	private Usuario buscaUsuarioPorEmail(String username) {
		return usuarioBusiness.getUsuarioByEmail(username);
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
