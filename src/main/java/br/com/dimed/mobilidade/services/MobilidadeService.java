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
import br.com.dimed.mobilidade.datapoa.vo.ItinerarioPontosVo;
import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;
import br.com.dimed.mobilidade.dto.ItinerarioDTO;
import br.com.dimed.mobilidade.dto.OnibusDTO;
import br.com.dimed.mobilidade.entity.ItinerarioEntity;
import br.com.dimed.mobilidade.entity.OnibusEntity;
import br.com.dimed.mobilidade.exception.AcceptedException;
import br.com.dimed.mobilidade.exception.BadRequestException;
import br.com.dimed.mobilidade.repository.ItinerarioRepository;
import br.com.dimed.mobilidade.repository.OnibusRepository;

/**
 * 
 * @author Jefferson Rodrigues
 *
 */
@Service
public class MobilidadeService {

	@Autowired
	private MobilidadeClient mobilidadClient;
	
	@Autowired
	private OnibusRepository onibusRepository;
	
	@Autowired
	private ItinerarioRepository itinerarioRepository; 
	
	@Autowired
	private GeolocalizacaoService geolocalizacaoService;
	
	public List<OnibusVo> pesquisarOnibus(String nome) {
		List<OnibusVo> listaOnibusVo =  Optional.ofNullable(mobilidadClient.pesquisarOnibusPorNome(nome)).orElse(new ArrayList<OnibusVo>());
		List<OnibusEntity> item = Optional.ofNullable(onibusRepository.findAll()).orElse(new ArrayList<OnibusEntity>());
		return mergerOnibusEntity(listaOnibusVo, item);
	}


	public ItinerarioVo pesquisarItinerario(BigInteger idOnibus) {
		Optional<ItinerarioVo> itinerarioVo = Optional.ofNullable(mobilidadClient.pesquisarItinerario(idOnibus));
		Optional<List<ItinerarioEntity>> itinerarioEntity = Optional.ofNullable(itinerarioRepository.findByIdIdLinha(idOnibus));
		return mergerIntinerarioEntity(itinerarioVo.get(),itinerarioEntity.get());
		
	}


	private List<OnibusVo> mergerOnibusEntity(List<OnibusVo> listaOnibusVo, List<OnibusEntity> item) {
		List<OnibusVo> retorno = new ArrayList<OnibusVo>();
		List<BigInteger> listaId = item.stream().map(x->x.getId()).collect(Collectors.toList());
		retorno.addAll(listaOnibusVo.stream().filter(p-> !listaId.contains(p.getId())).collect(Collectors.toList()));
		retorno.addAll(item.stream().map(p->new OnibusVo(p)).collect(Collectors.toList()));
		return retorno;
	}
	
	
	private ItinerarioVo mergerIntinerarioEntity(ItinerarioVo itinerarioVo, List<ItinerarioEntity>  itinerarioEntity) {
		if(!itinerarioEntity.isEmpty()){
			return ItinerarioVo.toParseItinerarioVo(itinerarioEntity);
		}
		return itinerarioVo;
	}

	public OnibusVo salvar(@Valid OnibusDTO onibusDTO) {
		OnibusEntity onibusNovo = onibusDTO.toOnibusEntity();
		OnibusEntity onibusOld = Optional.ofNullable(this.pesquisarOnibus(onibusNovo.getId())).orElse(new OnibusVo()).toOnibusEntity();
		if(onibusNovo.equals(onibusOld)) {
			throw new AcceptedException("Onibus já cadastrado");
		}
		return new OnibusVo(onibusRepository.save(onibusNovo));

	}


	private OnibusVo pesquisarOnibus(BigInteger idLinha) {
		List<OnibusVo> listaOnibusVo =  Optional.ofNullable(mobilidadClient.pesquisarOnibusPorNome("")).orElse(new ArrayList<OnibusVo>());
		OnibusEntity onibusEntity = onibusRepository.findById(idLinha).orElse(new OnibusEntity());
		Optional<OnibusVo> onibusVo = null; 
		if(onibusEntity.getId()!=null) {
			return new OnibusVo(onibusEntity);
		}
		if(!listaOnibusVo.isEmpty()){
			onibusVo =listaOnibusVo.stream().filter(p->p.getId().equals(idLinha)).findAny();
			if(onibusVo.get()!=null) {
				return onibusVo.get();
			}
		}
		return new OnibusVo();
	}


	public ItinerarioVo salvarItinerario(ItinerarioDTO itinerarioDTO) {
		Optional<ItinerarioVo> itinerario = Optional.ofNullable(mobilidadClient.pesquisarItinerario(itinerarioDTO.getIdlinha()));
		if(!itinerario.isPresent()) {
			throw new BadRequestException("Linha de Onibus não Encontrada!");
		}
		List<ItinerarioEntity> itinerarioDB = Optional.ofNullable(itinerarioRepository.findByIdIdLinha(itinerarioDTO.getIdlinha())).orElse(new ArrayList<ItinerarioEntity>());
		List<ItinerarioEntity> itinerarioWS = ItinerarioEntity.toParseItinerarioVo(itinerario.get());
		List<ItinerarioEntity> itinerarioNovos = ItinerarioEntity.toParseItinerarioDTO(itinerarioDTO);
		Optional<OnibusEntity> onibusExiste = onibusRepository.findById(itinerarioDTO.getIdlinha());
		List<ItinerarioEntity> retorno = new ArrayList<ItinerarioEntity>();
		if(!onibusExiste.isPresent()) {
			OnibusEntity onibusEntity =  new OnibusEntity();
			onibusEntity.setId(itinerarioDTO.getIdlinha());
			onibusEntity.setCodigo(itinerario.get().getCodigo());
			onibusEntity.setNome(itinerario.get().getNome());
			onibusRepository.save(onibusEntity);
		}
		if(!itinerarioDB.isEmpty()) {
			if(itinerarioNovos.equals(itinerarioDB)) {
				throw new AcceptedException("Itinerario já cadastrado");
			}else {
				List<ItinerarioEntity> novo = new ArrayList<ItinerarioEntity>();
				List<ItinerarioEntity> ItinerarioSemAlteracao = itinerarioDB.stream().filter(p->{
					ItinerarioEntity novos = itinerarioNovos.stream().filter(item->item.getId().equals(p.getId())).findAny().orElse(new ItinerarioEntity());
					return !p.getId().equals(novos.getId());	
				}).collect(Collectors.toList());
				
				novo.addAll(itinerarioNovos);
				
				novo.addAll(ItinerarioSemAlteracao);
				itinerarioRepository.saveAll(novo);
				ItinerarioVo.toParseItinerarioVo(novo);
			}
		}else {
			
			List<ItinerarioEntity> ItinerarioSemAlteracao = itinerarioWS.stream().filter(p->{
				ItinerarioEntity novos = itinerarioNovos.stream().filter(item->item.getId().equals(p.getId())).findAny().orElse(new ItinerarioEntity());
				return !p.getId().equals(novos.getId());	
			}).collect(Collectors.toList());
			
			retorno.addAll(itinerarioNovos);
			retorno.addAll(ItinerarioSemAlteracao);
			itinerarioRepository.saveAll(retorno);
		}
		
		return ItinerarioVo.toParseItinerarioVo(retorno); 
	}

	public List<OnibusVo> itinerarioPorLocalizacao(Double lat, Double lng,Double raio) throws InterruptedException {
		List<OnibusVo> onibus = pesquisarOnibus("");
		List<OnibusVo> retorno = new ArrayList<OnibusVo>();
		for(OnibusVo item:onibus) {
			ItinerarioVo itinerarioVo = this.pesquisarItinerario(item.getId());
			Thread.currentThread().sleep(500);
			Optional<ItinerarioPontosVo> onibusValido  = itinerarioVo.getPontos().values().stream().filter(obj->{
				Double distanciaKM = geolocalizacaoService.distanciaEntrePonto(obj.getLat(),obj.getLng(),lat,lng);
				return distanciaKM.compareTo(raio)<=0;
			}).findAny();
			if(onibusValido.isPresent()) {
				retorno.add(item);
			}
		}
		
		return retorno;
	}
}
