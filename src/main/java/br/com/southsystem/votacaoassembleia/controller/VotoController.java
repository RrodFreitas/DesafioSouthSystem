package br.com.southsystem.votacaoassembleia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.votacaoassembleia.dto.VotoDTO;
import br.com.southsystem.votacaoassembleia.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/voto")
@Api(value="Envio dos Votos")
@CrossOrigin(origins = "*")
public class VotoController {
	
	private Logger log = LoggerFactory.getLogger(PautaController.class);
	
	@Autowired
	private VotoService votoService;
	
	@ApiOperation(value = "Listar votos.")
	@GetMapping("/listar")
	public List<VotoDTO> listar() {
		log.info("Listando os votos.");
		return votoService.recuperaVotos();
	}	
	
	@ApiOperation(value = "Cadastrar votos.")
	@PostMapping (value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean cadastrarVotos(
			@ApiParam(required = true, value = "Cadastro de votos de uma sessão.") @RequestBody VotoDTO votoDTO) {
		log.info("Votando a pauta!");
		return votoService.cadastrarVotosSessao(votoDTO);
	}
}
