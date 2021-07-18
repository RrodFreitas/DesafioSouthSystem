package br.com.southsystem.votacaoassembleia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

	private String cpf;
	private Integer idSessao;
	private String descVoto;
	private String nomeAssociado;
}
