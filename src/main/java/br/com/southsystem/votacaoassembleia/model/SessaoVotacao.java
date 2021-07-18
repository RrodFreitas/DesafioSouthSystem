package br.com.southsystem.votacaoassembleia.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(name = "id_pauta")
	private Integer id;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id_pauta")
	private Pauta pauta;
	
	@OneToOne(mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Associado associado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVotacaoPauta;
	
	//Em minutos
	@Column(name="periodoVotacao", nullable = false,  columnDefinition = "int default 1") 
	private Integer periodoVotacaoPauta;
	
}
