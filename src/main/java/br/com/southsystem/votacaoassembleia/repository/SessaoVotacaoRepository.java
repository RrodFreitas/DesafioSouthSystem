package br.com.southsystem.votacaoassembleia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends CrudRepository<SessaoVotacao, Integer>{

	@Transactional
	SessaoVotacao findByIdPauta(@Param("idPauta") Integer idPauta);

}