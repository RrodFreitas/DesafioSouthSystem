package br.com.southsystem.votacaoassembleia.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPauta;
	
	@NotNull
	private String assunto;
	
	@NotNull
	private String autor;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacaoPauta;
	
	@NotNull
	private Integer totalVotosFavoraveis;
	
	@NotNull
	private Integer totalVotosContra;
}
