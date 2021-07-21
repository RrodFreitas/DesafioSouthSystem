package br.com.southsystem.votacaoassembleia.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.southsystem.votacaoassembleia.model.Voto;
import br.com.southsystem.votacaoassembleia.model.VotoPK;

public interface VotoRepository extends CrudRepository<Voto, VotoPK>{
	
}
