package br.com.southsystem.votacaoassembleia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Associado {
	
	@Id
	private String cpf;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id_pauta")
	private SessaoVotacao sessaoVotacao;
	
	@NotNull
	private String descVoto;
	
	@NotNull
	private String nomeAssociado;
}
