package br.com.dimed.mobilidade.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dimed.mobilidade.entity.ItinerarioEntity;
import br.com.dimed.mobilidade.entity.OnibusEntity;

public interface ItinerarioRepository extends JpaRepository<ItinerarioEntity,BigInteger>{

}
