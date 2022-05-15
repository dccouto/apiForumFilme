package com.challenge.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.challenge.dto.FilmeOmdbTO;
import com.challenge.entities.Filme;
import com.challenge.entities.ListaFilme;
import com.challenge.entities.Usuario;
import com.challenge.enums.StatusAcesso;
import com.challenge.repository.FilmeRepository;
import com.challenge.repository.ListaFilmeRepository;

@Service
public class ListaFilmeBusiness {
	
	@Autowired
	ListaFilmeRepository listaFilmeRepository;
	
	@Autowired
	FilmeRepository filmeRepository;
	
	@Autowired
	FilmeBusiness filmeBusiness;
	
	@Autowired
	UsuarioBusiness usuarioBusiness;
	

	public List<ListaFilme> buscarListasDoParticipante(String email) {
		return listaFilmeRepository.findByUsuario_Email(email);
	}
	
	

	public ListaFilme criarListaFilme(ListaFilme lista, String username) throws Exception {
		try {
			if(existeListaComMesmoNome(lista.getNomeLista(), username)) {
				throw new Exception("Lista de filme já existe!");
			}
			
			Usuario usuario = buscaUsuarioPorEmail(username);
			lista.setUsuario(usuario);
			
			return listaFilmeRepository.save(lista);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			
	}
	
	public Usuario buscaUsuarioPorEmail(String email) throws Exception {
		return usuarioBusiness.getUsuarioByEmail(email);
	}


	public ListaFilme adicionarFilmeNaLista(Long idLista, Filme filme, String username) throws Exception {
		try {
			
			if(filmeJaAdicionadoNaLista(idLista, filme)) {
				throw new Exception("Filme já existe na lista");
			}

			if(filmeBusiness.verificarSeFilmeEstaSalvoNoBancoDados(filme.getImdbID())) {
				throw new Exception("Filme já existe na lista");
			}
	
			FilmeOmdbTO filmeApiExternaPorImdb = filmeBusiness.buscarFilmeApiExternaPorImdb(filme.getImdbID());
			filme = new Filme(filmeApiExternaPorImdb);
			
			ListaFilme listaFilmes = listaFilmeRepository.findById(idLista).orElseThrow();
			
			listaFilmes.getListaFilmes().add(filme);
			listaFilmes.setUsuario(buscaUsuarioPorEmail(username));
			return listaFilmeRepository.save(listaFilmes);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	private boolean filmeJaAdicionadoNaLista(Long idLista, Filme filme) throws Exception {

		try {
			
			ListaFilme listaFilmes = listaFilmeRepository.findByIdLista(idLista).orElseThrow();

			for (Filme filmeDaLista : listaFilmes.getListaFilmes()) {
				if(filmeDaLista.getImdbID() == filme.getImdbID()) {
					return true;
				}
			}		
			return false;
			
		} catch (Exception e) {
			throw new Exception("Falha ao verificar se filme já foi adicionado na lista");
		}
	}

	/**
	 * Verifica se já existe lista com o mesmo nome
	 * @param nomeLista
	 * @return {@link Boolean}
	 * */
	private boolean existeListaComMesmoNome(String nomeLista, String username) {
		return listaFilmeRepository.existsByUsuario_emailAndNomeLista(username,nomeLista);
	}

	/**
	 * Altera o status de visibilidade da {@link ListaFilme}}
	 * @param statusAcesso
	 * @param idLista
	 * */
	public ListaFilme alterarStatusLista(StatusAcesso statusAcesso, Long idLista, String username) throws Exception {
		
		try {
			if(statusAcesso == null) {
				throw new Exception();
			}
			return listaFilmeRepository.findById(idLista)
			.map(lista -> {
				if(username.equals(lista.getUsuario().getEmail())) {
					lista.setStatus(statusAcesso);
				}
				return listaFilmeRepository.save(lista);
			})
			.orElseThrow();
			
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public  List<FilmeOmdbTO> buscarFilmeListaPorFiltro(String filtro, String tipoFiltro, Long idLista, String username) throws Exception {
		try {
			verificaSeListaPertenceUsuario(idLista, username);
			
			List<Filme> filmesPorLista = filmeBusiness.getFilmesPorLista(idLista);
			List<FilmeOmdbTO> filmeOmdbTOs = new ArrayList<>();
			
			for (Filme filme : filmesPorLista) {
				filmeOmdbTOs.add(filmeBusiness.buscarFilmeApiExternaPorImdb(filme.getImdbID()));
			}
			
			if(!filmeOmdbTOs.isEmpty()) {
				
				return filtraFilmes(filmeOmdbTOs, filtro, tipoFiltro);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
				
	}
	
	private void verificaSeListaPertenceUsuario(Long idLista, String username) throws Exception {
		try {
			if(!listaFilmeRepository.existsByUsuario_emailAndIdLista(username, idLista)) {
				throw new Exception("Lista não pertence ao usuário");
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	private List<FilmeOmdbTO> filtraFilmes(List<FilmeOmdbTO> filmes,String filtro, String tipoFiltro) throws Exception{
		List<FilmeOmdbTO> filmesOmdbFiltrados = new ArrayList<>();
		switch (tipoFiltro.toUpperCase()) {
		case "DIRETOR":
			for (FilmeOmdbTO filme : filmes) {
				if(filme.getDiretor().compareToIgnoreCase(filtro) == BigDecimal.ZERO.intValue()) {
					filmesOmdbFiltrados.add(filme);
				}
			}
			
			break;
		case "ANO":
			for (FilmeOmdbTO filme : filmes) {
				if(filme.getAno().compareToIgnoreCase(filtro) == 0) {
					filmesOmdbFiltrados.add(filme);

				}
			}
			
			break;

		default:
			throw new Exception("Filtro inválido");
		}
		
		return filmesOmdbFiltrados;
		
	}



	public Page<ListaFilme> buscarTodasListaPublicas(Pageable pageable) throws Exception {
		try {
			return listaFilmeRepository.findByStatus(StatusAcesso.PUBLICO, pageable);
		} catch (Exception e) {
			throw new Exception("Falha ao buscar as listas");
		}
	}

	
}
