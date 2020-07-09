package br.com.dimed.mobilidade.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;

/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Entity
@Table(name = "t_onibus")
public class OnibusEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	private String codigo;

	private String nome;

	@OneToMany(mappedBy = "onibus")
	private List<ItinerarioEntity> itinerario;

	public OnibusEntity(OnibusVo onibusVo) {
		this.setCodigo(onibusVo.getCodigo());
		this.setNome(onibusVo.getNome());
		this.setId(onibusVo.getId());
	}

	public OnibusEntity() {
		// TODO Auto-generated constructor stub
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ItinerarioEntity> getItinerario() {
		return itinerario;
	}

	public void setItinerario(List<ItinerarioEntity> itinerario) {
		this.itinerario = itinerario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		OnibusEntity other = (OnibusEntity) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OnibusEntity [id=" + id + ", codigo=" + codigo + ", nome=" + nome + "]";
	}

}
