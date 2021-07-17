package br.com.southsystem.votacaoassembleia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.votacaoassembleia.dto.PautaDTO;
import br.com.southsystem.votacaoassembleia.service.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	private Logger log = LoggerFactory.getLogger(PautaController.class);
	
	@Autowired
	private PautaService pautaService;
	
	
	@ApiOperation(value = "Listar pautas cadastradas.")
	@GetMapping
	public List<PautaDTO> listar() {
		log.info("Listando as pautas da assembleia.");
		return pautaService.recuperaPautasAssembleia();
	}
	
	@ApiOperation(value = "Cadastrar nova pauta.")
	@PostMapping (value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean cadastrarPauta(
			@ApiParam(required = true, value = "Objeto pauta que será cadastrado") @RequestBody PautaDTO pautaDTO) {
		log.info("Efetuando cadastro de nova pauta.");
		return pautaService.cadastroNovaPauta(pautaDTO);
	}
	
//	@ApiOperation(value = "Cancelar Transação de histórico de débito")
//	@PostMapping(value = "/cancelTransactionDebitHistory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public boolean cancelTransactionDebitHistory(
//			@ApiParam(required = true, value = "Objeto com dados da transação") @RequestBody TransactionDebitHistoryDto object) {
//		return cancelTransactionService.cancelTransaction(object);
//	}
}