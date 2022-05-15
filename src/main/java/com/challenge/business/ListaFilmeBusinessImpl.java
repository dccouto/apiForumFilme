package com.challenge.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.challenge.business.interfaces.FilmeBusinessInterface;
import com.challenge.business.interfaces.ListaFilmeBusinessInterface;
import com.challenge.business.interfaces.UsuarioBusinessInterface;
import com.challenge.dto.FilmeOmdbDto;
import com.challenge.entities.Filme;
import com.challenge.entities.ListaFilme;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.exceptions.BusinessException;
import com.challenge.exceptions.ListaFilmeException;
import com.challenge.repository.ListaFilmeRepository;

@Service
class ListaFilmeBusinessImpl implements ListaFilmeBusinessInterface {

	@Autowired
	private ListaFilmeRepository listaFilmeRepository;

	@Autowired
	private FilmeBusinessInterface filmeBusiness;

	@Autowired
	private UsuarioBusinessInterface usuarioBusiness;

	@Override
	public List<ListaFilme> buscarListasDoParticipante(String email) {
		return listaFilmeRepository.findByUsuario_Email(email);
	}

	@Override
	public ListaFilme criarListaFilme(ListaFilme lista, String username) {
		existeListaComMesmoNome(lista.getNomeLista(), username);

		Usuario usuario = buscaUsuarioPorEmail(username);
		lista.setUsuario(usuario);

		return listaFilmeRepository.save(lista);

	}

	private void existeListaComMesmoNome(String nomeLista, String username) {
		if (listaFilmeRepository.existsByUsuario_emailAndNomeLista(username, nomeLista)) {
			throw new ListaFilmeException("Lista de filme já existe!");
		}
	}

	private Usuario buscaUsuarioPorEmail(String email) {
		return usuarioBusiness.getUsuarioByEmail(email);
	}

	@Override
	public ListaFilme adicionarFilmeNaLista(Long idLista, Filme filme, String username) {

		if (filmeJaAdicionadoNaLista(idLista, filme)) {
			throw new ListaFilmeException("Filme já existe na lista");
		}

		if (filmeBusiness.verificarSeFilmeEstaSalvoNoBancoDados(filme.getImdbID())) {
			throw new ListaFilmeException("Filme já existe na lista");
		}

		FilmeOmdbDto filmeApiExternaPorImdb = filmeBusiness.buscarFilmeApiExternaPorImdb(filme.getImdbID());
		filme = new Filme(filmeApiExternaPorImdb);

		ListaFilme listaFilmes = listaFilmeRepository.findById(idLista).orElseThrow(() -> new ListaFilmeException("Lista não encontrada."));

		listaFilmes.getListaFilmes().add(filme);
		listaFilmes.setUsuario(buscaUsuarioPorEmail(username));
		return listaFilmeRepository.save(listaFilmes);

	}

	private boolean filmeJaAdicionadoNaLista(Long idLista, Filme filme) {

		ListaFilme listaFilmes = listaFilmeRepository.findByIdLista(idLista).orElseThrow();

		for (Filme filmeDaLista : listaFilmes.getListaFilmes()) {
			if (filmeDaLista.getImdbID() == filme.getImdbID()) {
				return true;
			}
		}
		return false;

	}

	@Override
	public ListaFilme alterarStatusLista(StatusAcesso statusAcesso, Long idLista, String username) {

		if (statusAcesso == null) {
			throw new ListaFilmeException("Estatus não pode ser nulo");
		}
		return listaFilmeRepository.findById(idLista).map(lista -> {
			if (username.equals(lista.getUsuario().getEmail())) {
				lista.setStatus(statusAcesso);
			}
			return listaFilmeRepository.save(lista);
		}).orElseThrow();

	}

	@Override
	public List<FilmeOmdbDto> buscarFilmeListaPorFiltro(String filtro, String tipoFiltro, Long idLista,
			String username) {
		verificaSeListaPertenceUsuario(idLista, username);

		List<Filme> filmesPorLista = filmeBusiness.getFilmesPorLista(idLista);
		List<FilmeOmdbDto> filmeOmdbTOs = new ArrayList<>();

		for (Filme filme : filmesPorLista) {
			filmeOmdbTOs.add(filmeBusiness.buscarFilmeApiExternaPorImdb(filme.getImdbID()));
		}

		if (filmeOmdbTOs.isEmpty()) {
			throw new ListaFilmeException("Filme não encontrado");

		}

		return filtraFilmes(filmeOmdbTOs, filtro, tipoFiltro);

	}

	private void verificaSeListaPertenceUsuario(Long idLista, String username) {
		if (verificaSeListaPertenceAoUsuario(idLista, username) == false) {
			throw new ListaFilmeException("Lista não pertence ao usuário");
		}
	}

	private boolean verificaSeListaPertenceAoUsuario(Long idLista, String username) {
		return listaFilmeRepository.existsByUsuario_emailAndIdLista(username, idLista);
	}

	private static List<FilmeOmdbDto> filtraFilmes(List<FilmeOmdbDto> filmes, String filtro, String tipoFiltro) {
		List<FilmeOmdbDto> filmesOmdbFiltrados = new ArrayList<>();
		switch (tipoFiltro.toUpperCase()) {
		case "DIRETOR":
			for (FilmeOmdbDto filme : filmes) {
				if (filme.getDiretor().compareToIgnoreCase(filtro) == BigDecimal.ZERO.intValue()) {
					filmesOmdbFiltrados.add(filme);
				}
			}

			break;
		case "ANO":
			for (FilmeOmdbDto filme : filmes) {
				if (filme.getAno().compareToIgnoreCase(filtro) == 0) {
					filmesOmdbFiltrados.add(filme);
				}
			}
			break;
		default:
			throw new BusinessException("Filtro inválido");
		}

		return filmesOmdbFiltrados;

	}

	@Override
	public Page<ListaFilme> buscarTodasListaPublicas(Pageable pageable) {
		return listaFilmeRepository.findByStatus(StatusAcesso.PUBLICO, pageable);
	}

}
