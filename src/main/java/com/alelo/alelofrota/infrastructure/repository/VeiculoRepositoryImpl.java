package com.alelo.alelofrota.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.alelo.alelofrota.domain.filter.VeiculoFiltro;
import com.alelo.alelofrota.domain.model.Veiculo;
import com.alelo.alelofrota.domain.repository.VeiculoRepository;
import com.alelo.alelofrota.domain.repository.VeiculoRepositoryQueries;
import com.alelo.alelofrota.infrastructure.repository.spec.VeiculoSpecs;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	@Lazy
	private VeiculoRepository repository;

	@Override
	public Page<Veiculo> buscaPaginada(VeiculoFiltro veiculoFiltro, Pageable pageRequest) {
		return  repository.findAll(VeiculoSpecs.comFiltroIdPlacaStatus(veiculoFiltro),pageRequest);
	}

}
