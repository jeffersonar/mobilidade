package br.com.dimed.mobilidade.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;

@Embeddable
public class ItinerarioIdEntity implements Serializable  {

	private BigInteger idLinha;
	
	private Integer ordem;
	
	public ItinerarioIdEntity() {
		// TODO Auto-generated constructor stub
	}

	public ItinerarioIdEntity(BigInteger idLinha, Integer ordem) {
		super();
		this.idLinha = idLinha;
		this.ordem = ordem;
	}

	public BigInteger getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(BigInteger idLinha) {
		this.idLinha = idLinha;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLinha == null) ? 0 : idLinha.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
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
		ItinerarioIdEntity other = (ItinerarioIdEntity) obj;
		if (idLinha == null) {
			if (other.idLinha != null)
				return false;
		} else if (!idLinha.equals(other.idLinha))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		return true;
	}
	
}
