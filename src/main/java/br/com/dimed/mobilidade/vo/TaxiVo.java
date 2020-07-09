package br.com.dimed.mobilidade.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.dimed.mobilidade.entity.TaxiEntity;

public class TaxiVo {

	private String nomePonto;

	private Double lat;

	private Double lng;

	private Date datCadastro;

	public TaxiVo() {
		// TODO Auto-generated constructor stub
	}

	public TaxiVo(String[] line) throws ParseException {
		this.nomePonto = line[0];
		this.lat = Double.parseDouble(line[1]);
		this.lng = Double.parseDouble(line[2]);
		this.datCadastro = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(line[3]);
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

	@Override
	public String toString() {
		return "TaxiVo [nomePonto=" + nomePonto + ", lat=" + lat + ", lng=" + lng + ", datCadastro=" + datCadastro
				+ "]";
	}

	public static List<TaxiVo> toParseTaxiEntity(List<TaxiEntity> outFile) {
		List<TaxiVo> retorno = new ArrayList<TaxiVo>();
		outFile.forEach(p -> {
			TaxiVo taxiVo = new TaxiVo();
			taxiVo.setNomePonto(p.getNomePonto());
			taxiVo.setDatCadastro(p.getDatCadastro());
			taxiVo.setLat(p.getLat());
			taxiVo.setLng(p.getLng());
			retorno.add(taxiVo);
		});
		return retorno;
	}

}
