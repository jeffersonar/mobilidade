package br.com.dimed.mobilidade.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dimed.mobilidade.entity.ItinerarioEntity;
import br.com.dimed.mobilidade.entity.ItinerarioIdEntity;

public interface ItinerarioRepository extends JpaRepository<ItinerarioEntity,ItinerarioIdEntity>{

	public List<ItinerarioEntity> findByIdIdLinha(BigInteger idLinha);

}
