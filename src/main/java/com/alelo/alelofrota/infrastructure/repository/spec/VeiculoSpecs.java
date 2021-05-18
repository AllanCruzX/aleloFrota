package com.alelo.alelofrota.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.alelo.alelofrota.domain.filter.VeiculoFiltro;
import com.alelo.alelofrota.domain.model.Veiculo;

public class VeiculoSpecs {

	public static Specification<Veiculo> comFiltroIdPlacaStatus(VeiculoFiltro veiculoFiltro) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (veiculoFiltro.getVeiculoId() != null) {
				predicates.add(builder.equal(root.get("id"), veiculoFiltro.getVeiculoId()));
			}

			if (StringUtils.hasText(veiculoFiltro.getPlaca())) {

				String placaFleitrada = veiculoFiltro.getPlaca().replaceAll("^\\s+", "");

				predicates.add(builder.like(builder.upper(builder.trim(root.get("placa"))),
						"%" + placaFleitrada.toUpperCase() + "%"));

			}

			predicates.add(builder.equal(root.get("status"), veiculoFiltro.isStatus()));

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
