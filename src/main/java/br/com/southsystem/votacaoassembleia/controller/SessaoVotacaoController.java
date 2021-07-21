package br.com.southsystem.votacaoassembleia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.votacaoassembleia.dto.InsertSessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.dto.SessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/sessao")
@Api(value="Abertura das Sessões de Votação")
@CrossOrigin(origins = "*")
public class SessaoVotacaoController {

	private Logger log = LoggerFactory.getLogger(PautaController.class);
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@ApiOperation(value = "Abrir sessão de votação.")
	@PostMapping (value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Integer abrirSessaoVotacao(
			@ApiParam(required = true, value = "Abertura da sessão de votação") @RequestBody InsertSessaoVotacaoDTO sessaoVotacaoDTO) {
		log.info("Efetuando cadastro da nova sessão de votação.");
		return sessaoVotacaoService.cadastroNovaSessao(sessaoVotacaoDTO);
	}
	
	@SuppressWarnings("deprecation")
	@ApiOperation(value = "Recupera sessão de votação pelo código da Pauta.")
	@RequestMapping(value = "/listar/{idPauta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SessaoVotacaoDTO recuperaSessao(@PathVariable(required = true) Integer idPauta) {
		log.info("Recuperando sessão de votacao.");
		return sessaoVotacaoService.recuperaPautasAssembleia(idPauta);
	}
	
	@ApiOperation(value = "Listar sessões de votação cadastradas.")
	@GetMapping("/listar")
	public List<SessaoVotacaoDTO> listar() {
		log.info("Listando as sessões de votação cadastradas.");
		return sessaoVotacaoService.recuperaSessoesVotacoesAssembleia();
	}	
	
}
