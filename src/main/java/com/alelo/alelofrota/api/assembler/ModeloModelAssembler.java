package com.alelo.alelofrota.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alelo.alelofrota.api.model.ModeloModel;
import com.alelo.alelofrota.domain.model.Modelo;

@Component
public class ModeloModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ModeloModel toModel(Modelo modelo) {
		return modelMapper.map(modelo, ModeloModel.class);
	}

	public List<ModeloModel> toCollectionModel(List<Modelo> modelos) {
		return modelos.stream().map(modelo -> toModel(modelo)).collect(Collectors.toList());
	}

}
