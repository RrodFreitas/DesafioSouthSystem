package br.com.southsystem.votacaoassembleia.dto;

import java.util.Date;

import br.com.southsystem.votacaoassembleia.model.Associado;
import br.com.southsystem.votacaoassembleia.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SessaoVotacaoDTO {

	private Integer id;
	private Pauta pauta;
	private Associado associado;
	private Date dataVotacaoPauta;
	private Integer periodoVotacaoPauta;
}
