package com.alelo.alelofrota.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alelo.alelofrota.domain.model.Veiculo;

@Repository
public interface VeiculoRepository
		extends CustomJpaRepository<Veiculo, Long>, VeiculoRepositoryQueries, JpaSpecificationExecutor<Veiculo> {

	Optional<Veiculo> findByPlaca(String placa);

}
