package br.com.dimed.mobilidade.controllers;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.mobilidade.datapoa.vo.ItinerarioPontosVo;
import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;
import br.com.dimed.mobilidade.dto.ItinerarioDTO;
import br.com.dimed.mobilidade.dto.OnibusDTO;
import br.com.dimed.mobilidade.services.MobilidadeService;

@RestController
@RequestMapping(value = "/onibus")
public class OnibusController {
	
	@Autowired
	private MobilidadeService mobilidadeService;
	
	 @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<OnibusVo>> pesquisarOnibus(@RequestParam(value="nome",defaultValue = "", required = false )String nome,HttpServletResponse response) {
		 List<OnibusVo> retorno = mobilidadeService.pesquisarOnibus(nome);
		 return new ResponseEntity<>(retorno, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET,path = "/{idLinha}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<ItinerarioVo> pesquisarItinerario(@PathVariable(value="idLinha")Integer idLinha,HttpServletResponse response) {
		ItinerarioVo retorno= mobilidadeService.pesquisarItinerario(BigInteger.valueOf(idLinha));
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<OnibusVo> salvarOnibus(@RequestBody @Valid OnibusDTO onibusDTO,HttpServletResponse response) {
		 OnibusVo retorno= mobilidadeService.salvar(onibusDTO);
		 return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST,path = "/itinerario", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<ItinerarioVo> salvarItinerario(@RequestBody @Valid ItinerarioDTO onibusDTO,HttpServletResponse response) {
		 ItinerarioVo retorno= mobilidadeService.salvarItinerario(onibusDTO);
		 return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET,path = "/localizacao", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<OnibusVo>> pesquisarPontoPorLocalizacao(@RequestParam(value="lat")Double lat,@RequestParam(value="lng")Double lng,@RequestParam(value="raio")Double raio,HttpServletResponse response) throws InterruptedException {
		 List<OnibusVo> retorno = mobilidadeService.itinerarioPorLocalizacao(lat,lng,raio);
		 return new ResponseEntity<>(retorno, HttpStatus.OK);
	 }
	 
}
