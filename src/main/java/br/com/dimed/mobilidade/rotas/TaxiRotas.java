package br.com.dimed.mobilidade.rotas;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dimed.mobilidade.processo.TaxiProcesso;
import br.com.dimed.mobilidade.services.TaxiService;

@Component
public class TaxiRotas extends RouteBuilder  {

	@Autowired
	private TaxiService taxiService;
	
    
    @Autowired
    private TaxiProcesso taxiProcesso;
	
	@Override
	public void configure() throws Exception {
		  from("file:".concat(taxiService.getInPath()).concat("?include=.*dat&charset=utf-8&delay=5&noop=true"))
		  			  .log("Camel body: ${body}")
		  			  .process(taxiProcesso)
		  			  .setHeader(Exchange.FILE_NAME,simple("${file:name.noext}.done.dat"))
		  			  .to("file:".concat(taxiService.getOutPath()).concat("?charset=utf-8"));
		
	}

}
