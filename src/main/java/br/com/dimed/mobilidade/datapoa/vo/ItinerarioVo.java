package br.com.dimed.mobilidade.datapoa.vo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.mobilidade.entity.ItinerarioEntity;
import net.bytebuddy.asm.Advice.This;

/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItinerarioVo {

	@JsonProperty(value = "idlinha")
	private BigInteger idLinha;

	private String nome;

	private String codigo;

	private Map<Integer,ItinerarioPontosVo> pontos;

	public static ItinerarioVo jsonParseToObject(String json) {
		try {
			String novoJson = json.replace("\"0\":", "pontos:{\"0\":").replace("}}", "}}}");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			ItinerarioVo retorno = objectMapper.readValue(novoJson, ItinerarioVo.class);
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}

	public ItinerarioVo() {
		// TODO Auto-generated constructor stub
	}

	public Map<Integer, ItinerarioPontosVo> getPontos() {
		return pontos;
	}

	public void setPontos(Map<Integer, ItinerarioPontosVo> pontos) {
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

	@Override
	public String toString() {
		return "ItinerarioVo [idLinha=" + idLinha + ", nome=" + nome + ", codigo=" + codigo + ", pontos=" + pontos
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((idLinha == null) ? 0 : idLinha.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pontos == null) ? 0 : pontos.hashCode());
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
		ItinerarioVo other = (ItinerarioVo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (idLinha == null) {
			if (other.idLinha != null)
				return false;
		} else if (!idLinha.equals(other.idLinha))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontos == null) {
			if (other.pontos != null)
				return false;
		} else if (!pontos.equals(other.pontos))
			return false;
		return true;
	}

	public static ItinerarioVo toParseItinerarioVo(List<ItinerarioEntity> itinerarioEntity) {
		ItinerarioVo retorno = new ItinerarioVo();
		retorno.setPontos(new LinkedHashMap<Integer, ItinerarioPontosVo>());
		itinerarioEntity.forEach(p->{
			retorno.idLinha = p.getId().getIdLinha();
			retorno.codigo= p.getOnibus().getCodigo();
			retorno.nome = p.getOnibus().getNome();
			ItinerarioPontosVo pontos = new ItinerarioPontosVo();
			pontos.setLat(p.getLat());
			pontos.setLng(p.getLng());
			retorno.getPontos().put(p.getId().getOrdem(), pontos);
			
		});
		return retorno;
	}

}
