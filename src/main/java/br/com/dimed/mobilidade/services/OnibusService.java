package br.com.dimed.mobilidade.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.mobilidade.datapoa.rest.clients.MobilidadeClient;
import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;
import br.com.dimed.mobilidade.dto.OnibusDTO;
import br.com.dimed.mobilidade.entity.ItinerarioEntity;
import br.com.dimed.mobilidade.entity.OnibusEntity;
import br.com.dimed.mobilidade.repository.ItinerarioRepository;
import br.com.dimed.mobilidade.repository.OnibusRepository;

/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Service
public class OnibusService {

	@Autowired
	private MobilidadeClient mobilidadClient;
	
	@Autowired
	private OnibusRepository onibusRepository;
	
	@Autowired
	private ItinerarioRepository itinerarioRepository; 
	
	public List<OnibusVo> pesquisarOnibus(String nome) {
		List<OnibusVo> listaOnibusVo =  Optional.ofNullable(mobilidadClient.pesquisarOnibusPorNome(nome)).orElse(new ArrayList<OnibusVo>());
		List<OnibusEntity> item = Optional.ofNullable(onibusRepository.findAll()).orElse(new ArrayList<OnibusEntity>());
		return mergerOnibusEntity(listaOnibusVo, item);
	}


	public ItinerarioVo pesquisarItinerario(BigInteger idOnibus) {
		ItinerarioVo itinerarioVo = Optional.ofNullable(mobilidadClient.pesquisarItinerario(idOnibus)).orElse(new ItinerarioVo());
		ItinerarioEntity itinerarioEntity = itinerarioRepository.findById(idOnibus).orElse(new ItinerarioEntity());
		return mergerIntinerarioEntity(itinerarioVo,itinerarioEntity);
		
	}


	private List<OnibusVo> mergerOnibusEntity(List<OnibusVo> listaOnibusVo, List<OnibusEntity> item) {
		List<OnibusVo> retorno = new ArrayList<OnibusVo>();
		List<BigInteger> listaId = item.stream().map(x->x.getId()).collect(Collectors.toList());
		retorno.addAll(listaOnibusVo.stream().filter(p-> !listaId.contains(p.getId())).collect(Collectors.toList()));
		retorno.addAll(item.stream().map(p->new OnibusVo(p)).collect(Collectors.toList()));
		return retorno;
	}
	
	private ItinerarioVo mergerIntinerarioEntity(ItinerarioVo itinerarioVo, ItinerarioEntity itinerarioEntity) {
		if(itinerarioEntity.getIdLinha()!=null){
			return new ItinerarioVo(itinerarioEntity);
		}
		return itinerarioVo;
	}
}
