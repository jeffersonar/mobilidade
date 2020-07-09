package br.com.dimed.mobilidade.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.mobilidade.dto.TaxiDTO;
import br.com.dimed.mobilidade.entity.TaxiEntity;
import br.com.dimed.mobilidade.repository.TaxiRepository;
import br.com.dimed.mobilidade.vo.TaxiVo;

@Service
public class TaxiService {
	
	@Autowired
	private TaxiRepository taxiRepository;

	private final String pathOut = File.separator.concat("data").concat(File.separator).concat("out")
			.concat(File.separator);

	private final String pathIn = File.separator.concat("data").concat(File.separator).concat("in")
			.concat(File.separator);

	public String getInPath() {
		return this.getPathBase().concat(this.pathIn);
	}

	public String getOutPath() {
		return this.getPathBase().concat(this.pathOut);
	}

	public String getPathBase() {
		String homePath = System.getenv("HOMEPATH");
		homePath = System.getProperty("os.name").equals("Linux") ? homePath : pathWindows(homePath);
		return homePath != null ? homePath : "";
	}
    private String pathWindows(String homePath) {
        return new String("C:").concat(File.separator).concat(homePath);
    }

	public List<TaxiVo> processaArquivos(File arquivoEntrada) throws FileNotFoundException, IOException, ParseException {
		 BufferedReader file = new BufferedReader(new FileReader(arquivoEntrada));
	        List<TaxiVo> arquivoSaida = new ArrayList<TaxiVo>();
	        while (file.ready()) {
	            String[] line = file.readLine().split("#");
	            arquivoSaida.add(new TaxiVo(line));
	        }
	        file.close();
	        return arquivoSaida;
	}

	public void salvar(List<TaxiVo> outFile) {
		List<TaxiEntity> lista= TaxiEntity.toParseTaxiVo(outFile);
		taxiRepository.saveAll(lista);
	}


	public List<TaxiVo> pesquisarPorPonto(String nome) {
		List<TaxiEntity> lista= taxiRepository.findByNomePontoContaining(nome);
		return TaxiVo.toParseTaxiEntity(lista);
	}

	public TaxiVo salvarTaxi(@Valid TaxiDTO taxiDto) {
		TaxiEntity taxi = new TaxiEntity(taxiDto);
		taxi.setDatCadastro(new Date());
		taxiRepository.saveAndFlush(taxi);
		return taxi.toTaxiVo();
	}

}
