package br.com.dimed.mobilidade.entity;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Entity
@Table(name="t_itinerario")
public class ItinerarioEntity {

	@Id
	private BigInteger idLinha;
	
	private String nome;
	
	private String codigo;
	
    @OneToMany(mappedBy="itinerarioEntity")
    private List<ItinerarioPontoEntity> pontos;

    
	public List<ItinerarioPontoEntity> getPontos() {
		return pontos;
	}

	public void setPontos(List<ItinerarioPontoEntity> pontos) {
		this.pontos = pontos;
	}

	public BigInteger getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(BigInteger idLinha) {
		this.idLinha = idLinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
	
}
