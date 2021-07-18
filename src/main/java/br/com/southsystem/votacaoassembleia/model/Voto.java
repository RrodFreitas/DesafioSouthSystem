package br.com.southsystem.votacaoassembleia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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
@Table(name="Voto")
@IdClass(VotoPK.class)
public class Voto {
	
	@Id
	private String cpf;
	
	@Id
	private Integer idSessao;
	
	@NotNull
	private String descVoto;
	
	@NotNull
	private String nomeAssociado;
}
