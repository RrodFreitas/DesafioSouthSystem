package br.com.southsystem.votacaoassembleia.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.votacaoassembleia.dto.VotoDTO;
import br.com.southsystem.votacaoassembleia.model.Voto;
import br.com.southsystem.votacaoassembleia.repository.VotoRepository;

@Service
public class VotoService {

	private Logger log = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private JdbcPautaService jdbcPautaService;
	
	@Autowired
	private ModelMapper modelMapper;

	public boolean cadastrarVotosSessao(VotoDTO votoDTO) {
		Voto voto = null;
		voto = modelMapper.map(votoDTO, Voto.class);
		try {
			if(voto != null) {
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
}
