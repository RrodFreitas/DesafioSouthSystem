package br.com.southsystem.votacaoassembleia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JdbcPautaService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int atualizaVotosPauta(Integer idPauta, String descVoto) {
		String sqlUpdate = "";
		if(descVoto.equalsIgnoreCase("SIM"))
			sqlUpdate = "update pauta set totalVotosFavoraveis = totalVotosFavoraveis + 1, totalVotos = totalVotos + 1 where idPauta = ?";
		else if (descVoto.equalsIgnoreCase("NAO"))
			sqlUpdate = "update pauta set totalVotosContra = totalVotosContra + 1, totalVotos = totalVotos + 1 where idpauta = ?";
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do voto inválida! Tem que ser: SIM ou NAO.");

		return jdbcTemplate.update(sqlUpdate, idPauta);
	}
}
