package br.com.southsystem.votacaoassembleia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.southsystem.votacaoassembleia.dto.VotoDTO;
import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;
import br.com.southsystem.votacaoassembleia.model.Voto;
import br.com.southsystem.votacaoassembleia.model.VotoPK;
import br.com.southsystem.votacaoassembleia.repository.SessaoVotacaoRepository;
import br.com.southsystem.votacaoassembleia.repository.VotoRepository;

@Service
public class VotoService {

	private Logger log = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private JdbcPautaService jdbcPautaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<VotoDTO> recuperaVotos() {

		List<VotoDTO> listaVotoDTO = new ArrayList<VotoDTO>();

		for (Voto voto : votoRepository.findAll()) {
			listaVotoDTO.add(modelMapper.map(voto, VotoDTO.class));
		}

		if(listaVotoDTO.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhum voto!");
		}
		
		return listaVotoDTO;
	}
	
	public boolean cadastrarVotosSessao(VotoDTO votoDTO) {
		Voto voto = null;
		voto = modelMapper.map(votoDTO, Voto.class);
		try {
			if(voto != null) {
				validaVotacao(votoDTO);
				
				votoRepository.save(voto);
				
				jdbcPautaService.atualizaVotosPauta(votoDTO.getIdSessao(), voto.getDescVoto());
				return true;
			} else
				return false;	
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	private void validaVotacao(VotoDTO votoDTO) {
		VotoPK votoPk = new VotoPK();
		
		votoPk.setCpf(votoDTO.getCpf());
		votoPk.setIdSessao(votoDTO.getIdSessao());
		
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(votoDTO.getIdSessao()).orElse(null);

		if(sessaoVotacao == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de sessão não identificado para este voto!");
		} else {
			if(new Date().after(DateUtils.addMinutes(sessaoVotacao.getDataVotacaoPauta(), sessaoVotacao.getPeriodoVotacaoPauta()))) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão de votação da pauta encerrado!");
			}
		};

		if(votoRepository.findById(votoPk).orElse(null) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Voto já cadastrado por este associado!");
		};
		
		if(!votoDTO.getDescVoto().equalsIgnoreCase("SIM") && !votoDTO.getDescVoto().equalsIgnoreCase("NAO")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do voto inválida! Tem que ser: SIM ou NAO.");
		}
	}

}
