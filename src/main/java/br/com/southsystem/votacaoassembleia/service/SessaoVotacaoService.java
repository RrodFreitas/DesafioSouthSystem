package br.com.southsystem.votacaoassembleia.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.votacaoassembleia.dto.SessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;
import br.com.southsystem.votacaoassembleia.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
	
	private Logger log = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public boolean cadastroNovaSessao(SessaoVotacaoDTO sessaoDTO) {
		SessaoVotacao sessao = null;
		if(sessaoDTO.getPeriodoVotacaoPauta() == null || sessaoDTO.getPeriodoVotacaoPauta() < 1) {
			sessaoDTO.setPeriodoVotacaoPauta(1);
			log.info("Tempo minimo invalido! Corrigido para 1 minuto.");
		}
		sessao = modelMapper.map(sessaoDTO, SessaoVotacao.class);
		try {
			if(sessao != null) {
				sessaoVotacaoRepository.save(sessao);
				return true;
			} else
				return false;	
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
	
	public SessaoVotacaoDTO recuperaPautasAssembleia(Integer idPauta) {
		return modelMapper.map(sessaoVotacaoRepository.findById(idPauta),SessaoVotacaoDTO.class);
	}
}
