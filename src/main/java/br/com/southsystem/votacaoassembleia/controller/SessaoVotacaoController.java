package br.com.southsystem.votacaoassembleia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.votacaoassembleia.dto.SessaoVotacaoDTO;
import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;
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
	@PostMapping (value = "/abrir_sessao", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean abrirSessaoVotacao(
			@ApiParam(required = true, value = "Abertura da sessão de votação") @RequestBody SessaoVotacaoDTO sessaoVotacacoDTO) {
		log.info("Efetuando cadastro da nova sessão de votação.");
		return sessaoVotacaoService.cadastroNovaSessao(sessaoVotacacoDTO);
	}
	
	@ApiOperation(value = "Recupera sessão de votação pelo código da Pauta.")
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SessaoVotacaoDTO recuperaSessao(@PathVariable(required = true) Integer idPauta) {
		log.info("Recuperando sessão de votacao.");
		return sessaoVotacaoService.recuperaPautasAssembleia(idPauta);
	}
	
	
//	@ApiOperation(value = "Listar pautas cadastradas.")
//	@GetMapping("/listar")
//	public List<PautaDTO> listar() {
//		log.info("Listando as pautas da assembleia.");
//		return pautaService.recuperaPautasAssembleia();
//	}
//	
//	@ApiOperation(value = "Cadastrar nova pauta.")
//	@PostMapping (value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.CREATED)
//	public boolean cadastrarPauta(
//			@ApiParam(required = true, value = "Objeto pauta que será cadastrado") @RequestBody PautaDTO pautaDTO) {
//		log.info("Efetuando cadastro de nova pauta.");
//		return pautaService.cadastroNovaPauta(pautaDTO);
//	}
}
