package com.alelo.alelofrota.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alelo.alelofrota.domain.filter.VeiculoFiltro;
import com.alelo.alelofrota.domain.model.Veiculo;

public interface VeiculoRepositoryQueries {

	Page<Veiculo> buscaPaginada(VeiculoFiltro veiculoFiltro, Pageable pageRequest );
	
}
