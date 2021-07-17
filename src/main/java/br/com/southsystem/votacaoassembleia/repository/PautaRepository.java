package br.com.southsystem.votacaoassembleia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.southsystem.votacaoassembleia.model.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Integer>{

}
