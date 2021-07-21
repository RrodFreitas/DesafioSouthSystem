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

import br.com.southsystem.votacaoassembleia.dto.InsertPautaDTO;
import br.com.southsystem.votacaoassembleia.dto.PautaDTO;
import br.com.southsystem.votacaoassembleia.model.Pauta;
import br.com.southsystem.votacaoassembleia.repository.PautaRepository;

@Service
public class PautaService {
	
	private Logger log = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PautaDTO> recuperaPautasAssembleia() {

		List<PautaDTO> listaPautaDTO = new ArrayList<PautaDTO>();

		for (Pauta pauta : pautaRepository.findAll()) {
			listaPautaDTO.add(modelMapper.map(pauta, PautaDTO.class));
		}

		if(listaPautaDTO.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma pauta!");
		}
		
		return listaPautaDTO;
	}

	public Integer cadastroNovaPauta(InsertPautaDTO pautaDTO) {

		try {
			Pauta novaPauta = new Pauta();
			
			novaPauta.setAutor(pautaDTO.getAutor());
			novaPauta.setAssunto(pautaDTO.getAssunto());
			novaPauta.setDataCriacaoPauta(pautaDTO.getDataCriacaoPauta());
			novaPauta.setTotalVotosContra(0);
			novaPauta.setTotalVotosFavoraveis(0);
			novaPauta.setTotalVotos(0);
			
			pautaRepository.save(novaPauta);
			return novaPauta.getIdPauta();

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao inserir pauta!");
		}
	}

}
