package br.com.southsystem.votacaoassembleia.dto;

import br.com.southsystem.votacaoassembleia.model.SessaoVotacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {

	private String cpf;
	private SessaoVotacao sessaoVotacao;
	private String descVoto;
	private String nomeAssociado;
}
