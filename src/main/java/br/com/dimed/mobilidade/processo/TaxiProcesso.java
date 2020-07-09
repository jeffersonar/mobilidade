package br.com.dimed.mobilidade.processo;

import java.io.File;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dimed.mobilidade.services.TaxiService;
import br.com.dimed.mobilidade.vo.TaxiVo;

@Component
public class TaxiProcesso implements Processor{

	@Autowired
	private TaxiService taxiService;
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		 List<TaxiVo> outFile =  taxiService.processaArquivos(exchange.getIn().getBody(File.class));
		 taxiService.salvar(outFile);
	     exchange.getMessage().setBody(outFile.toString());
		
	}

}
