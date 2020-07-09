package br.com.dimed.mobilidade.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.dto.ItinerarioDTO;

/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Entity(name="ItinerarioEntity")
@Table(name = "t_itinerario")
public class ItinerarioEntity {

	@EmbeddedId
	private ItinerarioIdEntity id;

	private Double lat;

	private Double lng;

	@ManyToOne
	@JoinColumn(name = "idLinha",insertable = false,updatable = false)
	private OnibusEntity onibus;

	public OnibusEntity getOnibus() {
		return onibus;
	}

	public void setOnibus(OnibusEntity onibus) {
		this.onibus = onibus;
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

	public ItinerarioIdEntity getId() {
		return id;
	}

	public void setId(ItinerarioIdEntity id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((onibus == null) ? 0 : onibus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItinerarioEntity other = (ItinerarioEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (onibus == null) {
			if (other.onibus != null)
				return false;
		} else if (!onibus.equals(other.onibus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItinerarioEntity [id=" + id + ", lat=" + lat + ", lng=" + lng + ", onibus=" + onibus + "]";
	}

	public static List<ItinerarioEntity> toParseItinerarioDTO(ItinerarioDTO itinerarioDTO) {
		List<ItinerarioEntity> lista = new ArrayList<ItinerarioEntity>();
		itinerarioDTO.getPontos().forEach(value->{
			ItinerarioEntity item = new ItinerarioEntity();
			item.setId(new ItinerarioIdEntity());
			item.getId().setIdLinha(itinerarioDTO.getIdlinha());
			item.setLat(value.getLat());
			item.setLng(value.getLng());
			item.getId().setOrdem(value.getOrdem());
			lista.add(item);
		});
		return lista;
	}

	
	public static List<ItinerarioEntity> toParseItinerarioVo(ItinerarioVo itinerarioWS) {
		List<ItinerarioEntity> lista = new ArrayList<ItinerarioEntity>();
		itinerarioWS.getPontos().forEach((key,value)->{
			ItinerarioEntity item = new ItinerarioEntity();
			item.setId(new ItinerarioIdEntity());
			item.getId().setIdLinha(itinerarioWS.getIdLinha());
			item.setLat(value.getLat());
			item.setLng(value.getLng());
			item.getId().setOrdem(key);
			lista.add(item);
		});
		return lista;
	}
}
