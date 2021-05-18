package com.alelo.alelofrota.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alelo.alelofrota.api.model.FabricanteModel;
import com.alelo.alelofrota.domain.model.Fabricante;

@Component
public class FabricanteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FabricanteModel toModel(Fabricante fabricante) {
		return modelMapper.map(fabricante, FabricanteModel.class);
	}
	
	public List<FabricanteModel> toCollectionModel(List<Fabricante> fabricantes) {
		return fabricantes.stream()
				.map(fabricante -> toModel(fabricante))
				.collect(Collectors.toList());
	}
	
}
