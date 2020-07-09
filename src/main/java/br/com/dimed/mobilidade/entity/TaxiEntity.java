package br.com.dimed.mobilidade.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import br.com.dimed.mobilidade.dto.TaxiDTO;
import br.com.dimed.mobilidade.vo.TaxiVo;

@Entity
@Table(name = "t_onibus")
public class TaxiEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	private String nomePonto;
	
	private Double lat;
	
	private Double lng;
	
	private Date datCadastro;
	
	public TaxiEntity() {
		// TODO Auto-generated constructor stub
	}

	public TaxiEntity(@Valid TaxiDTO p) {
		this.setNomePonto(p.getNomePonto());
		this.setLat(p.getLat());
		this.setLng(p.getLng());
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public Date getDatCadastro() {
		return datCadastro;
	}

	public void setDatCadastro(Date datCadastro) {
		this.datCadastro = datCadastro;
	}

	public static List<TaxiEntity> toParseTaxiVo(List<TaxiVo> outFile) {
		List<TaxiEntity> retorno = new ArrayList<TaxiEntity>();
		outFile.forEach(p->{
			TaxiEntity taxiEntity = new TaxiEntity();
			taxiEntity.setNomePonto(p.getNomePonto());
			taxiEntity.setDatCadastro(p.getDatCadastro());
			taxiEntity.setLat(p.getLat());
			taxiEntity.setLng(p.getLng());
			retorno.add(taxiEntity);
		});
		return retorno;
	}

	public TaxiVo toTaxiVo() {
		TaxiVo taxiVo = new TaxiVo();
		taxiVo.setNomePonto(this.getNomePonto());
		taxiVo.setDatCadastro(this.getDatCadastro());
		taxiVo.setLat(this.getLat());
		taxiVo.setLng(this.getLng());
		return taxiVo;
	}
}
