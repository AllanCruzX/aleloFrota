package com.alelo.alelofrota.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alelo.alelofrota.api.model.VeiculoModelResmo;
import com.alelo.alelofrota.domain.model.Veiculo;

@Component
public class VeiculoModelResumoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public VeiculoModelResmo toModel(Veiculo veiculo) {
		return modelMapper.map(veiculo, VeiculoModelResmo.class);
	}
	
	public List<VeiculoModelResmo> toCollectionModel(List<Veiculo> veiculos) {
		return veiculos.stream()
				.map(veiculo -> toModel(veiculo))
				.collect(Collectors.toList());
	}
	
}
