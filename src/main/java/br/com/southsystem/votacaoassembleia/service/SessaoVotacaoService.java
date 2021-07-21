package br.com.southsystem.votacaoassembleia.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.southsystem.votacaoassembleia.dto.InsertSessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.dto.SessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.model.Pauta;
import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;
import br.com.southsystem.votacaoassembleia.repository.PautaRepository;
import br.com.southsystem.votacaoassembleia.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
	
	private Logger log = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Integer cadastroNovaSessao(InsertSessaoVotacaoDTO sessaoDTO) {

		if(sessaoDTO.getPeriodoVotacaoPauta() == null || sessaoDTO.getPeriodoVotacaoPauta() < 1) {
			sessaoDTO.setPeriodoVotacaoPauta(1);
			log.info("Tempo minimo invalido! Corrigido para 1 minuto.");
		}
		
		try {
			Pauta pauta = pautaRepository.findById(sessaoDTO.getIdPauta()).orElse(null);
			
			if(pauta == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O código da pauta informado não existe!");
			}
			
			SessaoVotacao sessao = new SessaoVotacao();
			
			sessao.setIdPauta(sessaoDTO.getIdPauta());
			sessao.setDataVotacaoPauta(sessaoDTO.getDataVotacaoPauta());
			sessao.setPeriodoVotacaoPauta(sessaoDTO.getPeriodoVotacaoPauta());

			sessaoVotacaoRepository.save(sessao);
			return sessaoDTO.getIdPauta();	
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao inserir sessão!");
		}
	}
	
	public SessaoVotacaoDTO recuperaPautasAssembleia(Integer idPauta) {
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findByIdPauta(idPauta);
		
		if(sessaoVotacao == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma sessão para votação!");
		} else {
			try {
				return modelMapper.map(sessaoVotacaoRepository.findByIdPauta(idPauta),SessaoVotacaoDTO.class);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível recuperar a sessão para votação!");
			}
		}

	}
	
	public List<SessaoVotacaoDTO> recuperaSessoesVotacoesAssembleia() {

		List<SessaoVotacaoDTO> listaSessaoVotacaoDTO = new ArrayList<SessaoVotacaoDTO>();

		for (SessaoVotacao sessaoVotacao : sessaoVotacaoRepository.findAll()) {
			listaSessaoVotacaoDTO.add(modelMapper.map(sessaoVotacao, SessaoVotacaoDTO.class));
		}

		if(listaSessaoVotacaoDTO.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma sessão de votação!");
		}
		
		return listaSessaoVotacaoDTO;
	}
}
