package br.com.dimed.mobilidade.datapoa.vo;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.dimed.mobilidade.entity.OnibusEntity;


/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnibusVo{

	private BigInteger id;
	
	private String codigo;
	
	private String nome;
	
	public OnibusVo() {
	}
	
	public OnibusVo(BigInteger id, String codigo, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
	}

	public OnibusVo(OnibusEntity onibusEntity) {
		super();
		this.id = onibusEntity.getId();
		this.codigo = onibusEntity.getCodigo();
		this.nome = onibusEntity.getNome();
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
}
