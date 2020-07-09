package br.com.dimed.mobilidade.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dimed.mobilidade.entity.OnibusEntity;
import br.com.dimed.mobilidade.entity.TaxiEntity;

public interface TaxiRepository extends JpaRepository<TaxiEntity,BigInteger>{


	public List<TaxiEntity> findByNomePontoContaining(String nome);

}
