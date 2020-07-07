package br.com.dimed.mobilidade.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_itinerario_ponto")
public class ItinerarioPontoEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	
	private Integer ordem;
	
	private Double lat;

	private Double lng;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLinha", nullable = false)
	private ItinerarioEntity itinerarioEntity;
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public ItinerarioEntity getItinerarioEntity() {
		return itinerarioEntity;
	}

	public void setItinerarioEntity(ItinerarioEntity itinerarioEntity) {
		this.itinerarioEntity = itinerarioEntity;
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
