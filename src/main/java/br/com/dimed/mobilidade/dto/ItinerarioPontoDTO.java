package br.com.dimed.mobilidade.dto;

import javax.validation.constraints.NotNull;

public class ItinerarioPontoDTO {
	@NotNull(message = "{lat.not.blank}")
	private Double lat;
	
	@NotNull(message = "{lng.not.blank}")
	private Double lng;
	
	@NotNull(message = "{ordem.not.blank}")
	private Integer ordem;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
}
