package br.com.dimed.mobilidade.datapoa.rest.clients;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;
import ch.qos.logback.classic.Logger;

/**
 *
 * @author Jefferson Rodrigues
 *
 */
@Component
public class MobilidadeClient {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Value("${datapoa.url}")
	private String restServerUrl;

	public List<OnibusVo> pesquisarOnibusPorNome(String nome) {
		try {
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			UriComponents params = UriComponentsBuilder.fromHttpUrl(this.restServerUrl).queryParam("a", "nc")
					.queryParam("t", "o").queryParam("p", nome).build();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
			restTemplate.getMessageConverters().add(converter);
			ResponseEntity<List<OnibusVo>> response = restTemplate.exchange(params.toUriString(), HttpMethod.GET,
					new HttpEntity<Object>(headers), new ParameterizedTypeReference<List<OnibusVo>>() {
					});
			return response.getBody();
		}catch (RuntimeException e) {
			return new ArrayList<OnibusVo>(); 
		}
	}

	public ItinerarioVo pesquisarItinerario(BigInteger idOnibus) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		UriComponents params = UriComponentsBuilder.fromHttpUrl(this.restServerUrl).queryParam("a", "il")
				.queryParam("p", idOnibus).build();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		restTemplate.getMessageConverters().add(converter);
		ResponseEntity<String> response = restTemplate.exchange(params.toUriString(), HttpMethod.GET,
				new HttpEntity<Object>(headers), String.class);
		return ItinerarioVo.jsonParseToObject(response.getBody());
	}
}
