package br.com.southsystem.votacaoassembleia.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Voto {
	
	@Id
	private String cpf;
	
	@ManyToMany
	@JoinTable(
			  name = "sessao_associado", 
			  joinColumns = @JoinColumn(name = "cpf"), 
			  inverseJoinColumns = @JoinColumn(name = "id_sessao"))
	private List<SessaoVotacao> sessaoVotacao;
	
	@NotNull
	private String descVoto;
	
	@NotNull
	private String nomeAssociado;
}
