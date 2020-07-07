package br.com.dimed.mobilidade.datapoa.vo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.mobilidade.entity.ItinerarioEntity;

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
			return new ItinerarioVo();
		}
	}

	public ItinerarioVo() {
		// TODO Auto-generated constructor stub
	}

	public ItinerarioVo(ItinerarioEntity itinerarioEntity) {
		this.idLinha = itinerarioEntity.getIdLinha();
		this.nome = itinerarioEntity.getNome();
		this.codigo= itinerarioEntity.getCodigo();
		itinerarioEntity.getPontos().stream().forEach(item->{		
			this.pontos.put(item.getOrdem(), new ItinerarioPontosVo(item.getLat(), item.getLng()));
		});
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

}
