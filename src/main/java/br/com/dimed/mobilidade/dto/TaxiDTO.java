package br.com.dimed.mobilidade.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class TaxiDTO {

	@NotNull(message = "{nome.not.blank}")
	private String nomePonto;

	@NotNull(message = "{lat.not.blank}")
	private Double lat;

	@NotNull(message = "{lng.not.blank}")
	private Double lng;

	public TaxiDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
	}

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

}
