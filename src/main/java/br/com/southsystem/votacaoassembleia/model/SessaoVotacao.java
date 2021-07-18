package br.com.southsystem.votacaoassembleia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SessaoVotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idSessao;
	
	private Integer idPauta;
		
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVotacaoPauta;
	
	//Em minutos
	@Column(name="periodoVotacao", nullable = false,  columnDefinition = "int default 1") 
	private Integer periodoVotacaoPauta;
	
}
