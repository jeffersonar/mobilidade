package br.com.dimed.mobilidade.controllers;

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
import br.com.dimed.mobilidade.dto.ItinerarioDTO;
import br.com.dimed.mobilidade.dto.OnibusDTO;
import br.com.dimed.mobilidade.dto.TaxiDTO;
import br.com.dimed.mobilidade.services.TaxiService;
import br.com.dimed.mobilidade.vo.TaxiVo;

@RestController
@RequestMapping(value = "/taxi")
public class TaxiController {
	
	@Autowired
	private TaxiService taxiService;
	
	
	 @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<TaxiVo>> pesquisarTaxi(@RequestParam(value="nome",defaultValue = "", required = false )String nome,HttpServletResponse response) {
		 List<TaxiVo> taxiVo= taxiService.pesquisarPorPonto(nome);
		 return new ResponseEntity<>(taxiVo, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<TaxiVo> salvarTaxi(@RequestBody @Valid TaxiDTO taxiDto,HttpServletResponse response) {
		 TaxiVo taxiVo= taxiService.salvarTaxi(taxiDto);
		 return new ResponseEntity<>(taxiVo, HttpStatus.CREATED);
	 }
	 
}
