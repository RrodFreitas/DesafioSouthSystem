package br.com.southsystem.votacaoassembleia.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

	private Integer idPauta;
	private String assunto;
	private String autor;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCriacaoPauta;
	private Integer totalVotosFavoraveis;
	private Integer totalVotosContra;
	private Integer totalVotos;

}
