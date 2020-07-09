package br.com.dimed.mobilidade.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ItinerarioDTO implements Serializable {

	@NotNull(message = "{idlinha.not.blank}")
	private BigInteger idlinha;
	
	private List<ItinerarioPontoDTO> pontos;


	public BigInteger getIdlinha() {
		return idlinha;
	}

	public void setIdlinha(BigInteger idlinha) {
		this.idlinha = idlinha;
	}

	public List<ItinerarioPontoDTO> getPontos() {
		return pontos;
	}

	public void setPontos(List<ItinerarioPontoDTO> pontos) {
		this.pontos = pontos;
	}
	
}
