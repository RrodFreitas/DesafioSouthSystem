package br.com.southsystem.votacaoassembleia.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.votacaoassembleia.controller.PautaController;
import br.com.southsystem.votacaoassembleia.dto.PautaDTO;
import br.com.southsystem.votacaoassembleia.model.Pauta;
import br.com.southsystem.votacaoassembleia.repository.PautaRepository;

@Service
public class PautaService {
	
	private Logger log = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<PautaDTO> recuperaPautasAssembleia() {
		List<PautaDTO> listaPautaDTO = new ArrayList<PautaDTO>();
		for (Pauta pauta : pautaRepository.findAll()) {
			listaPautaDTO.add(modelMapper.map(pauta, PautaDTO.class));
		}
		return listaPautaDTO;
	}
	
	public boolean cadastroNovaPauta(PautaDTO pautaDTO) {
		Pauta novaPauta = null;
		novaPauta = modelMapper.map(pautaDTO, Pauta.class);
		try {
			if(novaPauta != null) {
				pautaRepository.save(novaPauta);
				return true;
			} else
				return false;	
		} catch (Exception e) {
			return false;
		}
	}

}
