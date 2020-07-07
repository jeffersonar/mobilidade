package br.com.dimed.mobilidade.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Entity
@Table(name="t_onibus")
public class OnibusEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;
	
	private String codigo;
	
	private String nome;

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
}
