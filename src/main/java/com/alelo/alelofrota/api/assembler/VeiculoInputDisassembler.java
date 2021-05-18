package com.alelo.alelofrota.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alelo.alelofrota.api.model.input.VeiculoInput;
import com.alelo.alelofrota.domain.model.Veiculo;

@Component
public class VeiculoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Veiculo toDomainObject(VeiculoInput veiculoInput) {
		return modelMapper.map(veiculoInput, Veiculo.class);
	}
	
	public void copyToDomainObject(VeiculoInput VeiculoInput, Veiculo veiculo) {
		modelMapper.map(VeiculoInput, veiculo);
	}
	
}
