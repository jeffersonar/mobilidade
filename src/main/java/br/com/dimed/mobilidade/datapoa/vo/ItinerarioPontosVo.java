package br.com.dimed.mobilidade.datapoa.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItinerarioPontosVo {
	
	@JsonProperty(value="lat")
	private Double lat;
	
	@JsonProperty(value="lng")
	private Double lng;
	
	public ItinerarioPontosVo() {
		// TODO Auto-generated constructor stub
	}
	
	public ItinerarioPontosVo(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
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
