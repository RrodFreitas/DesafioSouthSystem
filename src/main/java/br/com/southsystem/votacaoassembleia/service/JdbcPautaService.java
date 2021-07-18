package br.com.southsystem.votacaoassembleia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcPautaService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int atualizaVotosPauta(Integer id_pauta, String descVoto) {
		String sqlUpdate = "";
		if(descVoto.equalsIgnoreCase("sim"))
			 sqlUpdate = "update pauta set totalVotosFavoraveis = totalVotosFavoraveis + 1 where id_pauta = ?";
		else 
			sqlUpdate = "update pauta set totalVotosContra = totalVotosContra + 1 where id_pauta = ?";
		return jdbcTemplate.update(sqlUpdate, id_pauta);
	}
}
