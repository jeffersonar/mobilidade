package br.com.dimed.mobilidade.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class OnibusDTO implements Serializable {


	@NotNull(message = "{idlinha.not.blank}")
	private BigInteger idlinha;

	@NotNull(message = "{codigo.not.blank}")
	private String codigo;

	@NotNull(message = "{nome.not.blank}")
	private String nome;

	public BigInteger getIdlinha() {
		return idlinha;
	}

	public void setIdlinha(BigInteger idlinha) {
		this.idlinha = idlinha;
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
