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

import br.com.dimed.mobilidade.datapoa.vo.ItinerarioVo;
import br.com.dimed.mobilidade.datapoa.vo.OnibusVo;
import br.com.dimed.mobilidade.dto.OnibusDTO;
import br.com.dimed.mobilidade.services.OnibusService;

@RestController
@RequestMapping(value = "/mobi")
public class MobilidadeController {
	
	@Autowired
	private OnibusService onibusService;
	
	 @RequestMapping(method = RequestMethod.GET,path = "/onibus", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<OnibusVo>> pesquisarOnibus(@RequestParam(value="nome",defaultValue = "", required = false )String nome,HttpServletResponse response) {
		 List<OnibusVo> retorno = onibusService.pesquisarOnibus(nome);
		 return new ResponseEntity<>(retorno, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET,path = "/onibus/{idLinha}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<ItinerarioVo> pesquisarItinerario(@PathVariable(value="idLinha")Integer idLinha,HttpServletResponse response) {
		ItinerarioVo retorno= onibusService.pesquisarItinerario(BigInteger.valueOf(idLinha));
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST,path = "/onibus", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<OnibusVo> pesquisarOnibus(@RequestBody @Valid OnibusDTO onibusDTO,HttpServletResponse response) {
		// OnibusVo retorno= onibusService.salvar(onibusDTO);
		 return new ResponseEntity<>(null, HttpStatus.CREATED);
	 }
	 
}
